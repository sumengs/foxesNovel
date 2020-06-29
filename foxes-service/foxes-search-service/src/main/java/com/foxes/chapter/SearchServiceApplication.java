package com.foxes.chapter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @date: 2020/6/27 10:01
 * @author: sumeng
 */

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = {"com.foxes.book.feign"})
public class SearchServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(SearchServiceApplication.class,args);
    }
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate(new OkHttp3ClientHttpRequestFactory());
    }
}
