package org.tanglizi.oyster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OysterFrontApplication {

    public static void main(String[] args) {
        SpringApplication.run(OysterFrontApplication.class, args);
    }

}
/*
* 多模块注意事项：
* 1. 修改根pom中<modules>, 添加分module
* 2. 子模块pom注意引用父模块<dependency>，<aritifactId>xxx-yyy</>
* 3. 各模块包名应相同
*
* */