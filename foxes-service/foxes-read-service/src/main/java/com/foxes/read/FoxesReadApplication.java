package com.foxes.read;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @date: 2020/6/28 16:07
 * @author: sumeng
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = {"com.foxes.read.dao"})
@EnableFeignClients(basePackages = {"com.foxes.read.feign"})
public class FoxesReadApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoxesReadApplication.class, args);
    }
}
