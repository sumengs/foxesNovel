package com.foxes.chapter.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ESrabbitMQConfig {
    /**
     * ES信息变更交换机
     */
    public static final String BOOK_ESUP_EXCHANGE = "bookinfo_esup_exchange";

    /**
     * ES信息变更队列
     */
    public static final String PAGE_CREATE_QUEUE = "bookinfo_esup_queue";

    /**
     * 声明交换机
     * @return
     */
    @Bean
    public Exchange bookInfoEsupExchange(){
        return ExchangeBuilder.fanoutExchange(BOOK_ESUP_EXCHANGE).durable(true).build();
    }

    /**
     * 声明队列
     * @return
     */
    @Bean
    public Queue bookinfoEsupQueue(){
        return new Queue(PAGE_CREATE_QUEUE);
    }

    /**
     * 绑定交换机和队列
     * @param exchange
     * @param queue
     * @return
     */
    @Bean
    public Binding binding(@Qualifier("bookInfoEsupExchange") Exchange exchange,
                           @Qualifier("bookinfoEsupQueue") Queue queue){
        return BindingBuilder.bind(queue).to(exchange).with("").noargs();
    }

}
