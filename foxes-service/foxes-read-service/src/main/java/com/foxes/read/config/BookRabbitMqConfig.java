package com.foxes.read.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @date: 2020/6/28 19:01
 * @author: sumeng
 */
@Configuration
public class BookRabbitMqConfig {

    /**
     * 定义交换机名称
     */
    public static final String BOOK_UP_EXCHANGE = "book_up_exchange";
    public static final String BOOK_DELETE_EXCHANGE = "book_delete_exchange";

    /**
     * 定义队列名称
     */
    public static final String PAGE_CREATE_QUEUE = "page_create_queue";
    public static final String PAGE_DELETE_QUEUE = "page_delete_queue";

    /**
     * 声明队列
     *
     * @return queue
     */
    @Bean
    public Queue pageCreateQueue() {
        return new Queue(PAGE_CREATE_QUEUE);
    }

    @Bean
    public Queue pageDeleteQueue() {
        return new Queue(PAGE_DELETE_QUEUE);
    }

    /**
     * 声明交换机
     *
     * @return exchange
     */
    @Bean
    public Exchange bookUpExchange() {
        return ExchangeBuilder.fanoutExchange(BOOK_UP_EXCHANGE).durable(true).build();
    }

    @Bean
    public Exchange bookDeleteExchange() {
        return ExchangeBuilder.fanoutExchange(BOOK_DELETE_EXCHANGE).durable(true).build();
    }

    /**
     * 队列与交换机绑定
     *
     * @param exchange 交换机
     * @param queue 队列
     * @return 绑定
     */
    @Bean
    public Binding bookUpPageCreateBinding(@Qualifier("bookUpExchange") Exchange exchange, @Qualifier("pageCreateQueue") Queue queue) {
        return BindingBuilder.bind(queue).to(exchange).with("").noargs();
    }

    @Bean
    public Binding bookPageDeleteBinding(@Qualifier("bookDeleteExchange") Exchange exchange, @Qualifier("pageDeleteQueue") Queue queue) {
        return BindingBuilder.bind(queue).to(exchange).with("").noargs();
    }


}
