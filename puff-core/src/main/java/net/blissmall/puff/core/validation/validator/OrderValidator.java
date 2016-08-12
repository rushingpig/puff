package net.blissmall.puff.core.validation.validator;

import net.blissmall.puff.core.validation.annotation.IsOrder;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @Author : pigo
 * @Date : 16/4/23 下午1:37
 * @E-mail : zhenglin.zhu@xfxb.net
 */
public class OrderValidator implements ConstraintValidator<IsOrder,String> {

    private String value;

    @Override
    public void initialize(IsOrder constraintAnnotation) {
        value = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        return true;
    }
}
