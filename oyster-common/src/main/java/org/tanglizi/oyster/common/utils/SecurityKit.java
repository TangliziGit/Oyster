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
        return StringUtils.isBlank(referer);
    }

    public static boolean isCsrfBlocked(String csrfToken){
        GlobalCacheKit globalCache= GlobalCacheKit.getCacheSingleton();

        // Blocked by csrf_token
        return null == csrfToken || null == globalCache.get(csrfToken);
    }

    public static boolean isOperationTooFrequent(String ip, long interval, String... arguments){
        GlobalCacheKit globalCache=GlobalCacheKit.getCacheSingleton();

        String ipWithArgs=ip;
        StringBuilder stringBuilder=new StringBuilder();

        for (String argument: arguments)
            stringBuilder.append(":").append(argument);

        ipWithArgs+=stringBuilder.toString();

        Object lastPostTime = globalCache.get(ipWithArgs);
        if (null != lastPostTime) {
            long realInterval = System.currentTimeMillis() / 1000 - (Long) lastPostTime / 1000;
            if (realInterval < interval){
                globalCache.set(ipWithArgs, System.currentTimeMillis());
                return true;
            }
        }

        return false;
    }

    public enum SecurityBlockType{
        REFERER,
        CSRF
    }
}
