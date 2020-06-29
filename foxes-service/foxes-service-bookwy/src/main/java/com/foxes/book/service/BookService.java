package com.foxes.book.service;

import com.foxes.book.pojo.Book;

import java.util.List;

public interface BookService {

    List<Book> findAll();
    Book findOne(String id);
}
