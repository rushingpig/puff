package net.blissmall.puff.api.user;


import net.blissmall.puff.domain.user.AppUserAuths;
import net.blissmall.puff.domain.user.AppUserDeliveryAddress;
import net.blissmall.puff.domain.user.AppUserFavorites;
import net.blissmall.puff.domain.user.AppUserProfiles;
import net.blissmall.puff.vo.user.LoginVo;
import net.blissmall.puff.vo.user.RegistryLoginVo;
import net.blissmall.puff.vo.user.RegistryVo;
import org.springframework.validation.annotation.Validated;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author : zhuzhenglin
 * @Date : 16/8/7 00:06
 * @Email : zhenglin.zhu@xfxb.net
 * @Since : v1.0
 */
@Validated
public interface UserService {
    /**
     * 用户是否存在
     * @param username
     * @return
     */
    boolean userExist(String username);

    /**
     * 添加用户账号信息
     * @param registryLoginVo
     * @return 用户ID
     */
    String addUser(RegistryLoginVo registryLoginVo);

    /**
     * <pre>
     * 新增用户账号信息和基本信息
     * 成功返回用户ID,失败返回null
     * </pre>
     * @param registryLoginVo
     * @return
     */
    String addUserAndInfo(RegistryLoginVo registryLoginVo);

    /**
     * <pre>
     *  校验登录信息
     *  如果校验成功,则返回用户ID,否则,返回null
     * </pre>
     * @param loginVo
     * @return
     */
    String validateLogin(LoginVo loginVo);

    /**
     * 获取用户账号信息
     * @param appUserAuths
     * @return
     */
    AppUserAuths getUser(AppUserAuths appUserAuths);

    /**
     * 获取用户的基本信息
     * @param appUserProfiles
     * @return
     */
    AppUserProfiles getUserProfile(AppUserProfiles appUserProfiles);

    /**
     * 修改密码
     * @param registryVo
     * @return
     */
    int changePassword(RegistryVo registryVo);

    /**
     * 是否需要输入图片验证码
     * @param loginVo
     * @return
     */
    boolean overLogin(LoginVo loginVo);

    /**
     * 记录登录失败
     * @param loginVo
     */
    void recordLoginFailure(LoginVo loginVo);

    /**
     * 更新用户信息
     * @param appUserProfiles
     * @return
     */
    int updateUserInfo(AppUserProfiles appUserProfiles);

    /**
     * 新增用户基本信息
     * @param appUserProfiles
     * @return
     */
    int insertUserInfo(AppUserProfiles appUserProfiles);

    /**
     * 获取用户收藏列表
     * @param appUserFavorites
     * @return
     */
    List<AppUserFavorites> findAllFavorites(AppUserFavorites appUserFavorites);

    /**
     * 更新用户收藏信息
     * @param appUserFavorites
     * @return
     */
    int updateUserFavorites(AppUserFavorites appUserFavorites);

    /**
     * 新增用户收藏信息
     * @param appUserFavorites
     * @return
     */
    int insertUserFavorites(AppUserFavorites appUserFavorites);

    /**
     * 新增用户收货地址
     * @param appUserDeliveryAddress
     * @return
     */
    int addUserDeliveryAddress(AppUserDeliveryAddress appUserDeliveryAddress);

    /**
     * 根据用户ID获取所有的收货地址
     * @param appUserDeliveryAddress
     * @return
     */
    List<AppUserDeliveryAddress> findAllDeliveryAddressByUuid(AppUserDeliveryAddress appUserDeliveryAddress);

    /**
     * 根据收货地址id获取详情
     * @param appUserDeliveryAddress
     * @return
     */
    AppUserDeliveryAddress findDeliveryAddressById(AppUserDeliveryAddress appUserDeliveryAddress);

    /**
     * 删除(软)或者编辑用户收货地址
     * @param appUserDeliveryAddress
     * @return
     */
    int updateUserDeliveryAddress(AppUserDeliveryAddress appUserDeliveryAddress);

    /**
     * 设置默认的收货地址
     * @param deliveryAddressId
     */
    void defaultDeliveryAddress(Integer deliveryAddressId,String uuid);

    /**
     * 用户的收货地址个数是否超出限制
     * @param appUserDeliveryAddress
     * @return
     */
    boolean deliveryAddressOverLimitCount(AppUserDeliveryAddress appUserDeliveryAddress);

    /**
     * 用户是否已登录
     * @param session
     * @return
     */
    boolean userLogin(HttpSession session);

}
