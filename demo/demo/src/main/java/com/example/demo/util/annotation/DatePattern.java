package com.example.demo.util.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DatePattern {
    String pattern() default "dd/MM/yyyy";
    String message() default "Invalid data format.";
    String messageCode() default "COM108";
}
