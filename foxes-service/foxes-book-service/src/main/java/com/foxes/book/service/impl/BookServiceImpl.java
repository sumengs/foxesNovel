package com.foxes.book.service.impl;

import com.foxes.book.dao.BookMapper;
import com.foxes.book.pojo.Book;
import com.foxes.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @date: 2020/6/28 16:38
 * @author: sumeng
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    /**
     * 生成静态化页面
     * @param bookId
     */
    @Override
    public void generateHtml(String bookId) {

    }

    @Override
    public Book findBookById(String bookId) {

        return bookMapper.selectByPrimaryKey(bookId);
    }
}
