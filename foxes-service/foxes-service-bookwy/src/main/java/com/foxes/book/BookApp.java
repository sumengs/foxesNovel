package com.foxes.book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = "com.foxes.book.dao")
public class BookApp {
    public static void main(String[] args) {
        SpringApplication.run(BookApp.class,args);
    }
}
