package com.foxes.book.feign;

import com.foxes.book.pojo.Book;
import com.sumeng.peekshopping.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @date: 2020/6/30 14:36
 * @author: lenaminz
 */
@FeignClient(name = "book-service")
public interface BookFeign {
    /**
     * 根据id查询小说数据
     * @param bookId
     * @return
     */
    @ResponseBody
    @GetMapping("/book/findById/{id}")
    Result<Book> findById(@PathVariable("id") String bookId);

    /**
     * 查询所有Book数据
     * @return
     */
    @ResponseBody
    @GetMapping("/book/findAll")
    Result<List<Book>> findAll();
}
