package com.foxes.read.feign;

import com.foxes.read.pojo.Book;
import com.sumeng.peekshopping.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @date: 2020/6/30 14:36
 * @author: lenaminz
 */
@FeignClient(name = "read-service")
public interface BookFeign {
    /**
     * 根据id查询小说数据
     * @param bookId
     * @return
     */
    @GetMapping("/book/findById/{id}")
    Result<Book> findById(@PathVariable("id") String bookId);

    /**
     * 查询所有Book数据
     * @return
     */
    @GetMapping("/book/findAll")
    Result<List<Book>> findAll();

    /**
     * 查询阅读数前十的小说
     * @return
     */
    @GetMapping("/book/findTopTenRead")
    Result<List<Book>> findTopTenRead();

    /**
     * 查询订阅数数前十的小说
     * @return
     */
    @GetMapping("/book/findTopTenSubscribe")
    Result<List<Book>> findTopTenSubscribe();
}
