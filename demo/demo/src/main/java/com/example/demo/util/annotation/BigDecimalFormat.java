package com.example.demo.util.annotation;

import com.example.demo.model.constant.Constant;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface BigDecimalFormat {
    int scale() default 2;

    String maxValue() default Constant.MAX_BIG_DECIMAL_VALUE;

    String minValue() default Constant.MIN_BIG_DECIMAL_VALUE;

    boolean trailingZeros() default  false;

    String messageCode() default "COM115";

    String message() default "Exceeded maximum absolute value.";
}
