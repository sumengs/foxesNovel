package com.foxes.chapter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @date: 2020/6/27 10:00
 * @author: sumeng
 */

@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = {"com.foxes.chapter.dao"})
public class FoxesChapterApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoxesChapterApplication.class, args);
    }
}
