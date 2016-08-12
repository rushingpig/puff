package net.blissmall.puff.core.validation.annotation;


import net.blissmall.puff.core.validation.validator.IPValidator;

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
@Constraint(validatedBy = IPValidator.class)
@Documented
public @interface IsIP {
    String value() default "";
    String message() default "the ip address is not valid";

    // 分组
    Class<?>[] groups() default {};

    // 负载
    Class<? extends Payload>[] payload() default { };

    //指定多个时使用
    @Target({ElementType.FIELD,ElementType.METHOD,ElementType.PARAMETER,ElementType.ANNOTATION_TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        IsIP[] value();
    }
}
