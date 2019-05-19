package org.tanglizi.oyster.api.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.tanglizi.oyster.common.configurations.OysterCommonConfig;
import org.tanglizi.oyster.common.utils.GlobalCacheKit;
import org.tanglizi.oyster.common.utils.IPKit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PermisionInterceptor implements HandlerInterceptor {
    private Logger logger= LoggerFactory.getLogger(PermisionInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path=request.getContextPath();
        String uri=request.getRequestURI();
        String ip= IPKit.getIPAddress(request);

        logger.info("Request URI: "+uri);
        logger.info("Client IP  : "+ip);

        if (uri.startsWith(path+"/admin") && !uri.startsWith(path+"/admin/login")){
            HttpSession session = request.getSession();
            GlobalCacheKit globalCache=GlobalCacheKit.getCacheSingleton();
            Object userToken=session.getAttribute(OysterCommonConfig.USER_TOKEN);
            Object cachedUserToken=globalCache.get(OysterCommonConfig.USER_TOKEN);

            logger.info("userToken: "+userToken);
            logger.info("cachedUserToken: "+cachedUserToken);
            if (null == userToken || (null == cachedUserToken || !cachedUserToken.equals(userToken))) {
                // 注意重定向(Redirect)与转发(Forward)的区别
                logger.info("Forward to login");
                response.sendRedirect(path+"/admin/login");
                return false;
            }
        }

        return true;
    }

}
