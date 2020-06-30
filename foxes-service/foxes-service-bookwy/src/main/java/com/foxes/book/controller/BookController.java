package com.foxes.book.controller;


import com.foxes.book.pojo.Book;
import com.foxes.book.service.BookService;
import com.foxes.book.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RequestMapping("/book")
@RestController
public class BookController {

    @Autowired
    private BookService bookService;
    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/all")
    public List<Book> findAll(){
        List<Book> all = bookService.findAll();
        return all;
    }
    @RequestMapping("/findone/{bookId}")
    public Book findOne(@PathVariable("bookId") String bookId){
        return bookService.findOne(bookId);
    }

    @RequestMapping("/cate")
    public Map<Integer,String> findCate(){
        Map<Integer, String> map = categoryService.find();

        return map;
    }

}
