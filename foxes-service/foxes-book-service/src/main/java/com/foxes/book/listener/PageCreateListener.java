package com.foxes.book.listener;

import com.foxes.book.config.RabbitMQConfig;
import com.foxes.book.service.BookService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @date: 2020/6/28 19:26
 * @author: lenaminz
 */
@Component
public class PageCreateListener {
    @Autowired
    private BookService bookService;

    @RabbitListener(queues = RabbitMQConfig.PAGE_CREATE_QUEUE)
    public void receiveCreateMessage(String bookId){
        System.out.println("获取到生成静态化页面的小说id,id的值为:   "+bookId);
        bookService.generateHtml(bookId);
    }

    @RabbitListener(queues = RabbitMQConfig.PAGE_DELETE_QUEUE)
    public void receiveDeleteMessage(String bookId){
        System.out.println("获取到删除静态化页面的小说id,id的值为:   "+bookId);
        bookService.deleteHtml(bookId);
    }


}
