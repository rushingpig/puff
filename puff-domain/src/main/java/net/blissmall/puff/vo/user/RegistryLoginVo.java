package net.blissmall.puff.vo.user;

import net.blissmall.puff.core.validation.group.*;
import net.blissmall.puff.core.validation.group.user.*;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @Author : zhuzhenglin
 * @Date : 16/8/9 11:18
 * @Email : zhenglin.zhu@xfxb.net
 * @Since : v1.0
 */
public class RegistryLoginVo implements Serializable {

    @NotBlank(message = "{NotBlank.RegistryLoginVo.username}",
            groups = {FirstGroup.class,LoginGroup.class, RegistryGroup.class, QuickLoginGroup.class, ValidateUsernameGroup.class,OverLoginGroup.class})
    @Pattern(regexp = "^((13[0-9])|(14[5,7,9])|(15[^4,\\D])|(17[0,1,3,5-8])|(18[0-9]))\\d{8}$",
            message = "{Pattern.RegistryLoginVo.username}",
            groups = {SecondGroup.class,LoginGroup.class, RegistryGroup.class, QuickLoginGroup.class, ValidateUsernameGroup.class,OverLoginGroup.class})
    private String username;

    @NotBlank(message = "{NotBlank.RegistryLoginVo.password}",
            groups = {SecondGroup.class,RegistryGroup.class,ResetPwdGroup.class})
    @Pattern(regexp = "^[0-9a-zA-Z]{8}$",
            message = "{Pattern.RegistryVo.password}",
            groups = {FirstGroup.class,RegistryGroup.class,ResetPwdGroup.class})
    private String password;

    @NotBlank(message = "{NotBlank.RegistryLoginVo.smsValidateCode}",
            groups = {QuickLoginGroup.class,RegistryGroup.class})
    private String smsValidateCode;

    @NotBlank(message = "{NotBlank.RegistryLoginVo.imageValidateCode}",
            groups = {OverLoginGroup.class,RegistryGroup.class})
    private String imageValidateCode;

    private String ip;

    private AuthType authType;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSmsValidateCode() {
        return smsValidateCode;
    }

    public void setSmsValidateCode(String smsValidateCode) {
        this.smsValidateCode = smsValidateCode;
    }

    public String getImageValidateCode() {
        return imageValidateCode;
    }

    public void setImageValidateCode(String imageValidateCode) {
        this.imageValidateCode = imageValidateCode;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public AuthType getAuthType() {
        return authType;
    }

    public void setAuthType(AuthType authType) {
        this.authType = authType;
    }

    public enum AuthType{
        MOBILEPHONE,QQ,WEIXIN;
    }
}
