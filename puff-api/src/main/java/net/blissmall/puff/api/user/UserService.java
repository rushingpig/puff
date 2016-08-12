package net.blissmall.puff.api.user;


import net.blissmall.puff.domain.user.AppUserAuths;
import net.blissmall.puff.vo.user.LoginVo;
import net.blissmall.puff.vo.user.RegistryVo;
import org.springframework.validation.annotation.Validated;

/**
 * @Author : zhuzhenglin
 * @Date : 16/8/7 00:06
 * @Email : zhenglin.zhu@xfxb.net
 * @Since : v1.0
 */
@Validated
public interface UserService {

    boolean userExist(String username);

    boolean addUser(RegistryVo registryVo);

    boolean validateLogin(LoginVo loginVo);

    AppUserAuths getUser(AppUserAuths appUserAuths);

    int changePassword(RegistryVo registryVo);

}
