package com.example.demo.model.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Constant {
    public static final String NAME_USER = "Kiên";
    public static final int AGE_USER = 25;

    public static final String MAX_BIG_DECIMAL_VALUE = "99999999999999999999.99";
    public static final String MIN_BIG_DECIMAL_VALUE = "-99999999999999999999.99";

    public static final String FIELD_VALUE = "fieldValue";

    public static final String PERMISSION_PREFIX = "PERMISSION";
    public static final String ROLE_PERMISSION_CONNECTOR = "_";
    public static final String ROLE_PREFIX = "ROLE";

    public static final String SYSTEM = "system";

    private static final String EMAIL_REGEX = "^(?=.{1,}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    private static final String PASSWORD_REGEX = "^(?=.{0,})((?=.*[^a-zA-Z\\s])(?=.*[a-z])(?=.*[A-Z])|(?=.*[^a-zA-Z0-9\\s])(?=.*\\d)(?=.*[a-zA-Z])).*$";
}
