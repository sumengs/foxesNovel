package com.foxes.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @date: 2020/6/24 11:47
 * @author: sumeng
 */

@SpringBootApplication
@EnableEurekaServer
public class FoxesEurekaApplication {
    public static void main(String[] args) {
        SpringApplication.run(FoxesEurekaApplication.class, args);
    }
}
