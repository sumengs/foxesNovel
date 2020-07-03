package com.foxes.homepage.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @date: 2020/7/3 9:49
 * @author: sumeng
 */
@Configuration
public class AdRabbitMQConfig {

    /**
     * 定义交换机
     */
    public static final String AD_EXCHANGE = "ad_exchange";

    /**
     * 定义队列
     */
    public static final String AD_QUEUE = "ad_queue";


    /**
     * 声明交换机
     *
     * @return
     */
    @Bean
    public Exchange adExchange() {
        return ExchangeBuilder.fanoutExchange(AD_EXCHANGE).durable(true).build();
    }


    /**
     * 声明队列
     *
     * @return
     */
    @Bean
    public Queue adQueue() {
        return new Queue(AD_QUEUE);
    }


    /**
     * 绑定交换机
     *
     * @param exchange 交换机
     * @param queue    队列
     * @return
     */
    @Bean
    public Binding adChangeBindingAdExchange(@Qualifier("adExchange") Exchange exchange, @Qualifier("adQueue") Queue queue) {
        return BindingBuilder.bind(queue).to(exchange).with("").noargs();
    }

}
