package com.example.demo.model.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Constant {
    public static final String NAME_USER = "Kiên";
    public static final int AGE_USER = 25;

    public static final String MAX_BIG_DECIMAL_VALUE = "99999999999999999999.99";
    public static final String MIN_BIG_DECIMAL_VALUE = "-99999999999999999999.99";

    public static final String FIELD_VALUE = "fieldValue";

    /**
     * Phuc vu xuat file excel
     */
    public class ColumnType{
        public static final String SINGLE_COLUMN ="Single";
        public static final String LISTED_COLUMN ="List";

    }
}
