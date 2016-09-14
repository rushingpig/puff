package net.blissmall.puff.service.impl.user;

import com.github.pagehelper.PageHelper;
import net.blissmall.puff.api.regionalism.RegionalismService;
import net.blissmall.puff.api.user.UserLogService;
import net.blissmall.puff.api.user.UserService;
import net.blissmall.puff.common.utils.CryptoUtils;
import net.blissmall.puff.common.utils.StringUtils;
import net.blissmall.puff.common.utils.ToolUtils;
import net.blissmall.puff.common.utils.UUIDUtils;
import net.blissmall.puff.core.mapper.MyMapper;
import net.blissmall.puff.domain.user.AppUserAuths;
import net.blissmall.puff.domain.user.AppUserDeliveryAddress;
import net.blissmall.puff.domain.user.AppUserFavorites;
import net.blissmall.puff.domain.user.AppUserProfiles;
import net.blissmall.puff.service.constant.ErrorStatus;
import net.blissmall.puff.service.constant.PuffNamedConstant;
import net.blissmall.puff.service.exception.BussException;
import net.blissmall.puff.service.impl.BaseService;
import net.blissmall.puff.service.mapper.user.AppUserAuthsMapper;
import net.blissmall.puff.service.mapper.user.AppUserDeliveryAddressMapper;
import net.blissmall.puff.service.mapper.user.AppUserFavoritesMapper;
import net.blissmall.puff.service.mapper.user.AppUserProfilesMapper;
import net.blissmall.puff.vo.user.LoginVo;
import net.blissmall.puff.vo.user.RegistryLoginVo;
import net.blissmall.puff.vo.user.RegistryVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


/**
 * 用户相关的服务操作
 * @Author : zhuzhenglin
 * @Date : 16/8/7 00:07
 * @Email : zhenglin.zhu@xfxb.net
 * @Since : v1.0
 */
@Service
public class UserServiceImpl extends BaseService implements UserService {


    @Value("${puff.user.overlogin.count}")
    private Integer overLoginCount;

    @Resource
    private UserLogService userLogService;

    @Resource
    private AppUserAuthsMapper appUserAuthsMapper;
    @Resource
    private AppUserProfilesMapper appUserProfilesMapper;
    @Resource
    private AppUserFavoritesMapper appUserFavoritesMapper;
    @Resource
    private AppUserDeliveryAddressMapper appUserDeliveryAddressMapper;
    @Resource
    private RegionalismService regionalismService;
    @Resource
    private RestTemplate restTemplate;
    @Resource(name = "stringRedisTemplate")
    StringRedisTemplate redisTemplate;

    @Override
    public boolean userExist(String username) {
        AppUserAuths user = new AppUserAuths();
        user.setAuthId(username);
        return getUser(user) != null;
    }

    @Override
    public String addUser(RegistryLoginVo registryLoginVo) {
        AppUserAuths appUserAuths = new AppUserAuths();
        String uuid = UUIDUtils.uuid();
        appUserAuths.setUuid(uuid);
        appUserAuths.setAuthId(registryLoginVo.getUsername());
        appUserAuths.setAuthToken(CryptoUtils.MD5(registryLoginVo.getPassword()));
//        appUserAuths.setAuthType(AppUserAuths.AuthType.MOBILEPHONE.name());
        appUserAuthsMapper.insertSelective(appUserAuths);
        return uuid;
    }

    @Override
    @Transactional
    public String addUserAndInfo(RegistryLoginVo registryLoginVo) {
        String uuid = addUser(registryLoginVo);

        AppUserProfiles appUserProfiles = new AppUserProfiles();
        appUserProfiles.setUuid(uuid);
        appUserProfiles.setNickName(getNickname(registryLoginVo));
        int result = appUserProfilesMapper.insertSelective(appUserProfiles);
        if(result > 0){
            return uuid;
        }
        return null;
    }

    @Override
    public String validateLogin(LoginVo loginVo) {
        AppUserAuths user = new AppUserAuths();
        user.setAuthId(loginVo.getUsername());
        AppUserAuths appUserAuths = getUser(user);
        if(appUserAuths == null){
            throw new BussException(ErrorStatus.USER_NOT_EXIST);
        }
        String inputPassword = loginVo.getPassword();
        String realPassword = appUserAuths.getAuthToken();
        String uuid = appUserAuths.getUuid();
        boolean verify = StringUtils.equals(CryptoUtils.MD5(inputPassword),realPassword);
        logger.info("准备异步记录日志。。。");
        if(verify){
            // 异步记录登录日志
            Future<Integer> result = userLogService.recordLoginLog(loginVo,appUserAuths.getUuid());
            try {
                Integer insertId = result.get();
                if(insertId < 1){
                    logger.error("记录用户登录信息异常");
                }
            } catch (InterruptedException e) {
                logger.error("记录用户登录信息异常{}",e);
            } catch (ExecutionException e) {
                logger.error("记录用户登录信息异常{}",e);
            }
            return uuid;
        }
        return null;
    }

    @Override
    public AppUserAuths getUser(AppUserAuths appUserAuths) {
        return appUserAuthsMapper.selectOne(appUserAuths);
    }

    @Override
    public AppUserProfiles getUserProfile(AppUserProfiles appUserProfiles) {
        return appUserProfilesMapper.selectByPrimaryKey(appUserProfiles.getUuid());
    }

    @Override
    public int changePassword(RegistryVo registryVo) {
        Example example = new Example(AppUserAuths.class);
        AppUserAuths appUserAuths = new AppUserAuths();
        appUserAuths.setAuthToken(CryptoUtils.MD5(registryVo.getPassword()));
        example.createCriteria().andEqualTo("authId",registryVo.getUsername());
        return appUserAuthsMapper.updateByExampleSelective(appUserAuths,example);
    }

    @Override
    public boolean overLogin(LoginVo loginVo) {
        String loginFailRecordKey = getOverLoginCount(loginVo);
        String value = redisTemplate.opsForValue().get(loginFailRecordKey);
        return StringUtils.isNotBlank(value) && Long.valueOf(value) >= overLoginCount;
    }

    @Override
    public void recordLoginFailure(LoginVo loginVo) {
        String loginFailRecordKey = getOverLoginCount(loginVo);
        redisTemplate.opsForValue().increment(loginFailRecordKey,1);
    }



    @Override
    public int updateUserInfo(AppUserProfiles appUserProfiles) {
        return appUserProfilesMapper.updateByPrimaryKeySelective(appUserProfiles);
    }

    @Override
    public int insertUserInfo(AppUserProfiles appUserProfiles) {
        return appUserProfilesMapper.insertSelective(appUserProfiles);
    }

    @Override
    public List<AppUserFavorites> findAllFavorites(AppUserFavorites appUserFavorites) {
        Integer pageNo = appUserFavorites.getPageNo();
        String uuid = appUserFavorites.getUuid();
        PageHelper.startPage(pageNo,PuffNamedConstant.FAVORITE_PER_PAGE,false);
        PageHelper.orderBy("id desc");
        Example example = new Example(AppUserFavorites.class);
        example.createCriteria().andEqualTo("uuid",uuid).andEqualTo("delFlag", MyMapper.DelFlag.VALID.getStatus());
        return appUserFavoritesMapper.selectByExample(example);
    }

    @Override
    public int updateUserFavorites(AppUserFavorites appUserFavorites) {
        return appUserFavoritesMapper.updateByPrimaryKeySelective(appUserFavorites);
    }

    @Override
    public int insertUserFavorites(AppUserFavorites appUserFavorites) {
        return appUserFavoritesMapper.insertSelective(appUserFavorites);
    }

    @Override
    public int addUserDeliveryAddress(AppUserDeliveryAddress appUserDeliveryAddress) {
        return appUserDeliveryAddressMapper.insertUseGeneratedSelectiveKeys(appUserDeliveryAddress);
    }

    @Override
    public List<AppUserDeliveryAddress> findAllDeliveryAddressByUuid(AppUserDeliveryAddress appUserDeliveryAddress) {
        appUserDeliveryAddress.setDelFlag(MyMapper.DelFlag.VALID.getStatus());
        List<AppUserDeliveryAddress> list = appUserDeliveryAddressMapper.select(appUserDeliveryAddress);
        Map<String,String> idNameMap = regionalismService.getRegionalismIdNameMap();
        for(AppUserDeliveryAddress curr : list){
            String provinceName = idNameMap.get(curr.getProvinceId().toString());
            String cityName = idNameMap.get(curr.getCityId().toString());
            String regionalismName = idNameMap.get(curr.getRegionalismId().toString());
            String address = ToolUtils.purgeDBColumnValue(provinceName) + "," + ToolUtils.purgeDBColumnValue(cityName) + "," + ToolUtils.purgeDBColumnValue(regionalismName) + " " + ToolUtils.purgeDBColumnValue(curr.getAddress());
            curr.setAddress(address);
        }
        return list;
    }

    @Override
    public AppUserDeliveryAddress findDeliveryAddressById(AppUserDeliveryAddress appUserDeliveryAddress) {
        return appUserDeliveryAddressMapper.selectByPrimaryKey(appUserDeliveryAddress.getId());
    }

    @Override
    public int updateUserDeliveryAddress(AppUserDeliveryAddress appUserDeliveryAddress) {
        int result = appUserDeliveryAddressMapper.updateByPrimaryKeySelective(appUserDeliveryAddress);
        return result;
    }

    @Override
    @Transactional
    public void defaultDeliveryAddress(Integer deliveryAddressId,String uuid) {
        // 取消旧的默认收货地址
        AppUserDeliveryAddress oldAddress = new AppUserDeliveryAddress();
        oldAddress.setIsDefault(0);
        Example example = new Example(AppUserDeliveryAddress.class);
        example.createCriteria().andEqualTo("uuid",uuid).andEqualTo("isDefault",1);
        appUserDeliveryAddressMapper.updateByExampleSelective(oldAddress,example);
        // 设置新的默认地址
        AppUserDeliveryAddress address = new AppUserDeliveryAddress();
        address.setId(deliveryAddressId);
        address.setIsDefault(1);
        appUserDeliveryAddressMapper.updateByPrimaryKeySelective(address);
    }

    @Override
    public boolean deliveryAddressOverLimitCount(AppUserDeliveryAddress appUserDeliveryAddress) {
        appUserDeliveryAddress.setDelFlag(MyMapper.DelFlag.VALID.getStatus());
        return PuffNamedConstant.DEVLIERY_ADDRESS_LIMIT_COUNT <= appUserDeliveryAddressMapper.selectCount(appUserDeliveryAddress);
    }

    @Override
    public boolean userLogin(HttpSession session) {
        if(session.getAttribute(PuffNamedConstant.USER_SESSION_KEY) != null){
            return true;
        }
        return false;
    }
}
