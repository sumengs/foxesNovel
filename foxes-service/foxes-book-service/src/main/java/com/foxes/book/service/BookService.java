package com.foxes.book.service;

import com.foxes.book.pojo.Book;
import com.foxes.book.pojo.Category;

import java.util.List;

/**
 * @date: 2020/6/28 16:38
 * @author: sumeng
 */
public interface BookService {

    /**
     * 生成静态化页面
     * @param bookId
     */
    void generateHtml(String bookId);

    /**
     * 删除静态化页面
     * @param bookId
     */
    void deleteHtml(String bookId);

    /**
     * 根据Id查询Book数据
     * @param bookId bookID
     * @return Book
     */
    Book findBookById(String bookId);

    /**
     * 查询所有Book数据
     * @return
     */
    List<Book> findAll();

    /**
     * 查询阅读数前十的小说
     * @return
     */
    List<Book> findByReadNumDesc();

    /**
     * 查询订阅数前十的小说
     * @return
     */
    List<Book> findBySubscribeNumDesc();

    /**
     * 根据作者查询小说列表
     * @param author
     * @return
     */
    List<Book> findListByAuthor(String author);
}
