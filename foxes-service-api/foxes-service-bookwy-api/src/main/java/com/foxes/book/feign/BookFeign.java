package com.foxes.book.feign;

import com.foxes.book.pojo.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@FeignClient("book")
public interface BookFeign {

    /**
     * 查找所有book
     * @return book的list集合
     */
    @RequestMapping("/book/all")
    public List<Book> findAll();

    /**
     * 根据bookId查询book
     * @param bookId
     * @return book
     */
    @RequestMapping("/book/findone/{bookId}")
    public Book findOne(@PathVariable("bookId") String bookId);


    /**
     * 获取分类信息
     * @return
     */
    public Map<Integer,String> findCate();
}
