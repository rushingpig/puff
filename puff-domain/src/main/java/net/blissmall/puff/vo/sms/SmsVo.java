package net.blissmall.puff.vo.sms;

import net.blissmall.puff.core.validation.group.SendSmsGroup;
import net.blissmall.puff.core.validation.group.user.ValidateCodeGroup;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 发送短信的vo
 * @Author : zhuzhenglin
 * @Date : 16/8/9 19:31
 * @Email : zhenglin.zhu@xfxb.net
 * @Since : v1.0
 */
public class SmsVo implements Serializable {

    @Pattern(regexp = "^((13[0-9])|(14[5,7,9])|(15[^4,\\D])|(17[0,1,3,5-8])|(18[0-9]))\\d{8}$",
            message = "{Pattern.SmsVo.phoneNum}",
            groups = {SendSmsGroup.class, ValidateCodeGroup.class})
    private String phoneNum;
    @NotNull(message = "{NotBlank.SmsVo.smsType}",
            groups = {SendSmsGroup.class, ValidateCodeGroup.class})
    private SmsType smsType;

    @Length(min = 4,message = "{Length.SmsVo.code}",
            groups = {ValidateCodeGroup.class})
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public SmsType getSmsType() {
        return smsType;
    }

    public void setSmsType(SmsType smsType) {
        this.smsType = smsType;
    }

    public enum SmsType{
        /**
         * 注册
         */
        REGISTRY,
        /**
         * 快速登录
         */
        QUIDK_LOGIN,
        /**
         * 重置密码
         */
        RESET_PASSWORD
    }
}
