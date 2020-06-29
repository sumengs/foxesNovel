package com.foxes.book.feign;

import com.foxes.book.pojo.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient("book")
public interface BookFeign {
    @RequestMapping("/book/all")
    public List<Book> findAll();

    @RequestMapping("/book/findone/{bookId}")
    public Book findOne(@PathVariable("bookId") String bookId);
}
