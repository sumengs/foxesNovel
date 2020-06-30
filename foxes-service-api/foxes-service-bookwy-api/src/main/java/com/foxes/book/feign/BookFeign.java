package com.foxes.book.feign;

import com.foxes.book.pojo.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@FeignClient("book")
public interface BookFeign {
    @RequestMapping("/book/all")
    public List<Book> findAll();

    @RequestMapping("/book/findone/{bookId}")
    public Book findOne(@PathVariable("bookId") String bookId);

    @RequestMapping("/book/cate")
    public Map<Integer,String> findCate();
}
