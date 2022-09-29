package com.example.demo.util.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelEntity {
    String applicationName() default "Excel Service";

    String applicationVersion() default "1.0";

    String sheetName() default "Sheet 1";

    Class<?>[] validators() default {};
}
