package net.blissmall.puff.validation.annotation;


import net.blissmall.puff.validation.validator.SmsTypeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @Author : pigo
 * @Date : 5/10/16 14:02
 * @E-mail : zhenglin.zhu@xfxb.net
 */
@Target({ElementType.FIELD,ElementType.METHOD,ElementType.PARAMETER,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SmsTypeValidator.class)
@Documented
public @interface SmsType {
    String value() default "";
    String message() default "the smsType is not valid";

    // 分组
    Class<?>[] groups() default {};

    // 负载
    Class<? extends Payload>[] payload() default { };

    //指定多个时使用
    @Target({ElementType.FIELD,ElementType.METHOD,ElementType.PARAMETER,ElementType.ANNOTATION_TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        SmsType[] value();
    }
}
