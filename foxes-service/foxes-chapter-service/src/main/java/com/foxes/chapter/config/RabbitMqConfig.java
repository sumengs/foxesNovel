package com.foxes.chapter.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @date: 2020/6/30 16:20
 * @author: sumeng
 */
@Configuration
public class RabbitMqConfig {


    /**
     * 定义chapter交换机
     */
    public static final String CHAPTER_EXCHANGE = "chapter_exchange";

    /**
     * 定义chapter队列
     */
    public static final String CHAPTER_PAGE_QUEUE = "chapter_page_queue";


    /**
     * 声明chapter交换机
     *
     * @return
     */
    @Bean
    public Exchange chapterExchange() {
        return ExchangeBuilder.fanoutExchange(CHAPTER_EXCHANGE).durable(true).build();
    }


    /**
     * 声明队列
     *
     * @return
     */
    @Bean
    public Queue chapterPageQueue() {
        return new Queue(CHAPTER_PAGE_QUEUE);
    }


    @Bean
    public Binding chapterPageChangeBindingChapterExchange(@Qualifier("chapterExchange") Exchange exchange, @Qualifier("chapterPageQueue") Queue queue) {
        return BindingBuilder.bind(queue).to(exchange).with("").noargs();
    }


    /**
     * 定义chapter交换机
     */
    public static final String CHAPTER_DELETE_EXCHANGE = "chapter_delete_exchange";

    /**
     * 定义chapter队列
     */
    public static final String CHAPTER_DELETE_QUEUE = "chapter_Delete_queue";


    /**
     * 声明chapter交换机
     *
     * @return
     */
    @Bean
    public Exchange chapterDeleteExchange() {
        return ExchangeBuilder.fanoutExchange(CHAPTER_DELETE_EXCHANGE).durable(true).build();
    }


    /**
     * 声明队列
     *
     * @return
     */
    @Bean
    public Queue chapterDeleteQueue() {
        return new Queue(CHAPTER_DELETE_QUEUE);
    }


    /**
     * 绑定交换机
     *
     * @param exchange 交换机
     * @param queue    队列
     * @return
     */
    @Bean
    public Binding chapterDeleteChangeBindingChapterDeleteExchange(@Qualifier("chapterDeleteExchange") Exchange exchange, @Qualifier("chapterDeleteQueue") Queue queue) {
        return BindingBuilder.bind(queue).to(exchange).with("").noargs();
    }
}
