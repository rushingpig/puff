package net.blissmall.puff.vo.user;

import java.io.Serializable;

/**
 * 注册时的请求vo类
 * @Author : zhuzhenglin
 * @Date : 16/8/9 11:22
 * @Email : zhenglin.zhu@xfxb.net
 * @Since : v1.0
 */
public class RegistryVo extends RegistryLoginVo implements Serializable{

    private String confirmPassword;

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
