package com.foxes.user.service;

import com.foxes.user.pojo.Book;

import java.util.List;

public interface BookService {
    //返回书架中的书(Book)
    List<Book> list();


    //添加书架
    void add(String bookId);

    //取消书架
    void delete(String bookId);

    //最近阅读的小说id添加到redis中
    void addTime(String bookId);

    //查询最近阅读
    List<Book> listFavourite(String bookId);
}
