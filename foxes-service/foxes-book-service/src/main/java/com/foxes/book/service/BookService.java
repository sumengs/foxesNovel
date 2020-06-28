package com.foxes.book.service;

import com.foxes.book.pojo.Book;

/**
 * @date: 2020/6/28 16:38
 * @author: sumeng
 */
public interface BookService {

    /**
     * 根据Id查询Book详细数据
     * @param bookId bookID
     * @return Book
     */
    Book findBookById(String bookId);

}
