package com.foxes.book.service.impl;

import com.foxes.book.pojo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.foxes.book.dao.BookMapper;
import com.foxes.book.service.BookService;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookMapper bookMapper;

    @Override
    public List<Book> findAll() {
        List<Book> books = bookMapper.selectAll();
        return books;
    }

    @Override
    public Book findOne(String id){
        Book book = bookMapper.selectByPrimaryKey(id);

        return book;
    }
}
