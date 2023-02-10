package com.example.demo.util;

import com.example.demo.model.constant.Constant;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelColumn {
    String name();
    String numberFormat() default "General";
    String columnType() default Constant.ColumnType.SINGLE_COLUMN;

}
