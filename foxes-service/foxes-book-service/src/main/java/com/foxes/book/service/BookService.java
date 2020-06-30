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

}
