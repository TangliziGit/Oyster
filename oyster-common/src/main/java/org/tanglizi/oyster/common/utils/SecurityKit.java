package org.tanglizi.oyster.common.utils;

import net.bytebuddy.utility.RandomString;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.util.HtmlUtils;
import org.tanglizi.oyster.common.configurations.OysterCommonConfig;

public class SecurityKit {
    public static void cleanXSS(String string){
        HtmlUtils.htmlEscape(string);
    }

    public static String getCrsfToken(){
        return RandomStringUtils.randomAlphanumeric(OysterCommonConfig.RANDOM_CRSF_TOKEN_LENGTH);
    }
}
