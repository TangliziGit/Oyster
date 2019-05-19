package org.tanglizi.oyster.common.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.util.HtmlUtils;
import org.tanglizi.oyster.common.configurations.OysterCommonConfig;

public class SecurityKit {
    public static void cleanXSS(String string){
        HtmlUtils.htmlEscape(string);
    }

    public static String getCsrfToken(){
        return RandomStringUtils.randomAlphanumeric(OysterCommonConfig.RANDOM_CSRF_TOKEN_LENGTH);
    }

    public static String getUserToken(){
        return RandomStringUtils.randomAlphanumeric(OysterCommonConfig.RANDOM_USER_TOKEN_LENGTH);
    }
}
