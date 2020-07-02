package com.foxes.homepage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @date: 2020/6/27 10:01
 * @author: sumeng
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.foxes.homepage.dao"})
@EnableFeignClients(basePackages = {"com.foxes.book.feign"})
public class HomepageServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(HomepageServiceApplication.class, args);
    }
}
