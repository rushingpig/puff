package net.blissmall.puff.core.validation.validator;

import net.blissmall.puff.common.utils.StringUtils;
import net.blissmall.puff.core.validation.annotation.IsIP;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * 判断是否为IP的校验类
 * @Author : pigo
 * @Date : 5/10/16 14:06
 * @E-mail : zhenglin.zhu@xfxb.net
 */
public class IPValidator implements ConstraintValidator<IsIP,String> {

    @Override
    public void initialize(IsIP constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(StringUtils.isBlank(value)){
            return false;
        }else {
            String regex = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
                    + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                    + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                    + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
            Pattern p = Pattern.compile(regex);
            return p.matcher(value).matches();
        }
    }
}
