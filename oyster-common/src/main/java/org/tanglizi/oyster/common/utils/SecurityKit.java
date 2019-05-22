package org.tanglizi.oyster.common.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.util.HtmlUtils;
import org.tanglizi.oyster.common.configurations.OysterCommonConfig;

import javax.servlet.http.HttpServletRequest;

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

    public static SecurityBlockType securityBlock(HttpServletRequest request, String csrfToken){
        String referer=request.getHeader("Referer");
        GlobalCacheKit globalCache= GlobalCacheKit.getCacheSingleton();

        // 这里应该匹配一下HOST
        // Blocked by referer
        if (StringUtils.isBlank(referer))
            return SecurityBlockType.REFERER;

        // Blocked by csrf_token
        if (OysterCommonConfig.CRSF_TOKEN.equals(globalCache.get(csrfToken)))
            return SecurityBlockType.CSRF;

        return null;
    }

    enum SecurityBlockType{
        REFERER,
        CSRF
    }
}
