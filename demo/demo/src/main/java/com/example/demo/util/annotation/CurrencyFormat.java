package com.example.demo.util.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface CurrencyFormat {
    String format() default "###.###.###";

    String messageCode() default "COM108";

    String message() default "Data invalid format .";
}
