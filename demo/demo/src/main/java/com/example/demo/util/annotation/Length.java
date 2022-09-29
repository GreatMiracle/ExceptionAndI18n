package com.example.demo.util.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Length {
    int max() default Integer.MAX_VALUE;
    int min() default 0;
    String message() default "Exceeded maximum length (<maxlength> characters).";
    String messageCode() default "COM107";
}
