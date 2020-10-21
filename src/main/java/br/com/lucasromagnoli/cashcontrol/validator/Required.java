package br.com.lucasromagnoli.cashcontrol.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author github.com/lucasromagnoli
 * @since 10/2020
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Required {
    ValidatorOperation[] operations() default ValidatorOperation.ALL;

    String message() default "";
}
