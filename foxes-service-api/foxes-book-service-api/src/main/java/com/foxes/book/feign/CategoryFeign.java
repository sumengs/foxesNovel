package com.foxes.book.feign;

import com.sumeng.peekshopping.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @date: 2020/6/30 14:48
 * @author: lenaminz
 */
@FeignClient(name = "book-service")
public interface CategoryFeign {
    /**
     * 查询所有分类信息
     * @return
     */
    @GetMapping("/category/findAll")
    Result findAll();
}
