package org.tanglizi.oyster.common.configurations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OysterCommonConfig {
    public static final String resourcePath="file:///home/tanglizi/oyster/resources/";
    public static final int RANDOM_CSRF_TOKEN_LENGTH =16;
    public static final int RANDOM_USER_TOKEN_LENGTH=16;
    public static final String CRSF_TOKEN="CRSF_TOKEN";
    public static final String USER_TOKEN="USER_TOKEN";
    public static final List<String> adminNameList=Arrays.asList("admin");
    public static final List<String> adminPasswordList=Arrays.asList("admin");

    public static final Boolean defaultAllowComment=true;
}
