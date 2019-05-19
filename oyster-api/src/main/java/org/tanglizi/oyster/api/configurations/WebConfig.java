package org.tanglizi.oyster.api.configurations;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.tanglizi.oyster.api.interceptor.PermisionInterceptor;

public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new PermisionInterceptor())
                .addPathPatterns("/api/v1/admin/**")
                .excludePathPatterns("/api/v1/admin/login");
    }
}
