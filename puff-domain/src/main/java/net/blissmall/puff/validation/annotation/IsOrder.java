package net.blissmall.puff.validation.annotation;


import net.blissmall.puff.validation.validator.OrderValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * the annotation for validating the orderId
 * @Author : pigo
 * @Date : 16/4/23 下午1:27
 * @E-mail : zhenglin.zhu@xfxb.net
 */
@Target({ElementType.FIELD,ElementType.METHOD,ElementType.PARAMETER,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = OrderValidator.class)
@Documented
public @interface IsOrder {

    String value() default "";

    String message() default "";

    // 分组
    Class<?>[] groups() default {};

    // 负载
    Class<? extends Payload>[] payload() default { };

    //指定多个时使用
    @Target({ElementType.FIELD,ElementType.METHOD,ElementType.PARAMETER,ElementType.ANNOTATION_TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        IsOrder[] value();
    }
}
