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
        if (SecurityKit.isRefererBlocked(request))
            return SecurityBlockType.REFERER;

        if (SecurityKit.isCsrfBlocked(csrfToken))
            return SecurityBlockType.CSRF;

        return null;
    }

    public static boolean isRefererBlocked(HttpServletRequest request){
        String referer=request.getHeader("Referer");

        // 这里应该匹配一下HOST
        // Blocked by referer
        if (StringUtils.isBlank(referer))
            return true;
        return false;
    }

    public static boolean isCsrfBlocked(String csrfToken){
        GlobalCacheKit globalCache= GlobalCacheKit.getCacheSingleton();
        // Blocked by csrf_token
        if (null == csrfToken || OysterCommonConfig.CRSF_TOKEN.equals(globalCache.get(csrfToken)))
            return true;
        return false;
    }

    public enum SecurityBlockType{
        REFERER,
        CSRF
    }
}
