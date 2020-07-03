package com.foxes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foxes.user.config.TokenDecode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @date: 2020/6/27 10:01
 * @author: sumeng
 */

@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = {"com.foxes.user.mapper","com.foxes.user.dao"})
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class);
    }

    @Bean
    public TokenDecode tokenDecode() {
        return new TokenDecode();
    }

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }
}

