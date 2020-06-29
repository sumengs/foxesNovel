package com.foxes.book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @date: 2020/6/28 16:07
 * @author: sumeng
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.foxes.book.dao"})
public class FoxesBookApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoxesBookApplication.class, args);
    }
}
