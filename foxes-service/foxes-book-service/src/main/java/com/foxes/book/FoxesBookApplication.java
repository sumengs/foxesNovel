package com.foxes.book;

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
@MapperScan(basePackages = {"com.foxes.book.dao"})
@EnableFeignClients(basePackages = {"com.foxes.chapter.feign"})
public class FoxesBookApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoxesBookApplication.class, args);
    }
}
