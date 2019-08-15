package org.tanglizi.oyster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.tanglizi.oyster.api.OysterApiApplication;
import org.tanglizi.oyster.back.OysterBackApplication;
import org.tanglizi.oyster.back.configurations.OysterBackConfig;
import org.tanglizi.oyster.common.OysterCommonApplication;
import org.tanglizi.oyster.front.OysterFrontApplication;

@SpringBootApplication
public class OysterRunnerApplication {

    public static void main(String[] args) {
        // 加上所有欲扫描包的模块
        // 不加OysterCommonApplication模块的话，不能扫描到common包的bean
        SpringApplication.run(new Class[]{
                OysterFrontApplication.class,
                OysterApiApplication.class,
                OysterCommonApplication.class,
                OysterBackApplication.class
        }, args);
    }

}
