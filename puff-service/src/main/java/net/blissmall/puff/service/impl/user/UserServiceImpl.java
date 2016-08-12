package net.blissmall.puff.service.impl.user;

import net.blissmall.puff.api.user.UserService;
import net.blissmall.puff.common.utils.CryptoUtils;
import net.blissmall.puff.common.utils.StringUtils;
import net.blissmall.puff.common.utils.UUIDUtils;
import net.blissmall.puff.domain.user.AppUserAuths;
import net.blissmall.puff.service.constant.ErrorStatus;
import net.blissmall.puff.service.exception.BussException;
import net.blissmall.puff.service.impl.BaseService;
import net.blissmall.puff.service.mapper.user.AppUserAuthsMapper;
import net.blissmall.puff.vo.user.LoginVo;
import net.blissmall.puff.vo.user.RegistryVo;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

/**
 * @Author : zhuzhenglin
 * @Date : 16/8/7 00:07
 * @Email : zhenglin.zhu@xfxb.net
 * @Since : v1.0
 */
@Service
public class UserServiceImpl extends BaseService implements UserService {

    @Resource
    private AppUserAuthsMapper appUserAuthsMapper;

    @Override
    public boolean userExist(String username) {
        AppUserAuths user = new AppUserAuths();
        user.setAuthId(username);
        return getUser(user) != null;
    }

    @Override
    public boolean addUser(RegistryVo registryVo) {
        AppUserAuths appUserAuths = new AppUserAuths();
        appUserAuths.setUuid(UUIDUtils.uuid());
        appUserAuths.setAuthId(registryVo.getUsername());
        appUserAuths.setAuthToken(CryptoUtils.MD5(registryVo.getPassword()));
        appUserAuths.setAuthType(AppUserAuths.AuthType.MOBILEPHONE.name());
        return appUserAuthsMapper.insertSelective(appUserAuths) > 0;
    }

    @Override
    public boolean validateLogin(LoginVo loginVo) {
        AppUserAuths user = new AppUserAuths();
        user.setAuthId(loginVo.getUsername());
        AppUserAuths appUserAuths = getUser(user);
        if(appUserAuths == null){
            throw new BussException(ErrorStatus.USER_NOT_EXIST);
        }
        String inputPassword = loginVo.getPassword();
        String realPassword = appUserAuths.getAuthToken();
        return StringUtils.equals(CryptoUtils.MD5(inputPassword),realPassword);
    }

    @Override
    public AppUserAuths getUser(AppUserAuths appUserAuths) {
        return appUserAuthsMapper.selectOne(appUserAuths);
    }

    @Override
    public int changePassword(RegistryVo registryVo) {
        Example example = new Example(AppUserAuths.class);
        AppUserAuths appUserAuths = new AppUserAuths();
        appUserAuths.setAuthToken(CryptoUtils.MD5(registryVo.getPassword()));
        example.createCriteria().andEqualTo("auth_id",registryVo.getUsername());
        return appUserAuthsMapper.updateByExampleSelective(appUserAuths,example);
    }
}
