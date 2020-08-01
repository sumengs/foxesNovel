package com.foxes.read.feign;

import com.foxes.read.pojo.Category;
import com.sumeng.peekshopping.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @date: 2020/6/30 14:48
 * @author: lenaminz
 */
@FeignClient(name = "read-service")
public interface CategoryFeign {
    /**
     * 查询所有分类信息
     * @return
     */
    @GetMapping("/category/findAll")
    Result<List<Category>> findAll();

    /**
     * 根据分类ID查询分类信息
     * @param id 分类id
     * @return
     */
    @GetMapping("/category/findByCategoryId/{id}")
    Result<Category> findByCategoryId(@PathVariable("id") Integer id);

    /**
     * 根据小说ID查询分类
     * @param bookId
     * @return
     */
    @GetMapping("/category/findByBookId/{bookId}")
    Result<Category> findByBookId(@PathVariable("bookId") String bookId);
}
