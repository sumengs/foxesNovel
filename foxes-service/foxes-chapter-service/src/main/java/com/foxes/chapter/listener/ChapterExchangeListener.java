package com.foxes.chapter.listener;

import com.foxes.chapter.config.RabbitMqConfig;
import com.foxes.chapter.service.ChapterService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @date: 2020/6/30 16:22
 * @author: sumeng
 */
@Component
public class ChapterExchangeListener {

    @Autowired
    private ChapterService chapterService;


    @RabbitListener(queues = RabbitMqConfig.CHAPTER_PAGE_QUEUE)
    public void updateMessage(String bookId) {
        System.out.println("获取到新增静态化页面的章节,id的值为:   " + bookId);
        chapterService.generateHtml(bookId);
    }


    @RabbitListener(queues = RabbitMqConfig.CHAPTER_DELETE_QUEUE)
    public void deleteMessage(String bookId) {
        System.out.println("获取到删除静态化页面的章节,id的值为:   " + bookId);
        chapterService.deleteHtml(bookId);
    }
}
