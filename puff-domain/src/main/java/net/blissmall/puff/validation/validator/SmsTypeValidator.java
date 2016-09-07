package net.blissmall.puff.validation.validator;

import net.blissmall.puff.validation.annotation.SmsType;
import net.blissmall.puff.vo.sms.SmsVo;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

/**
 * 判断是否为IP的校验类
 * @Author : pigo
 * @Date : 5/10/16 14:06
 * @E-mail : zhenglin.zhu@xfxb.net
 */
public class SmsTypeValidator implements ConstraintValidator<SmsType,SmsVo.SmsType> {

    @Override
    public void initialize(SmsType constraintAnnotation) {
    }

    @Override
    public boolean isValid(SmsVo.SmsType value, ConstraintValidatorContext context) {
        return Arrays.asList(SmsVo.SmsType.values()).contains(value);
    }
}
