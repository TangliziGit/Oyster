package org.tanglizi.oyster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OysterRunnerApplication {

    public static void main(String[] args) {
        SpringApplication.run(new Class[]{
                OysterFrontApplication.class,
                OysterApiApplication.class
        }, args);
    }

}
