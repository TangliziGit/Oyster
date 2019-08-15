package org.tanglizi.oyster.common.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.tanglizi.oyster.common.configurations.OysterCommonConfig;

@Configuration
public class OysterWebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // spring try to concat the url matched to ResourceLocations
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

        registry.addResourceHandler("/resources/images/**")
                .addResourceLocations(OysterCommonConfig.resourcePath +"images/");
    }

}
