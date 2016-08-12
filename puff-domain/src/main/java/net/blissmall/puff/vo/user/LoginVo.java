package net.blissmall.puff.vo.user;

import java.io.Serializable;

/**
 * @Author : zhuzhenglin
 * @Date : 16/8/9 11:21
 * @Email : zhenglin.zhu@xfxb.net
 * @Since : v1.0
 */
public class LoginVo extends RegistryLoginVo implements Serializable {

    private boolean rememberMe;

    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }
}
