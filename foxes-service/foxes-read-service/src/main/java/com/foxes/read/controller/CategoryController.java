package com.foxes.read.controller;

import com.foxes.read.pojo.Category;
import com.foxes.read.service.CategoryService;
import com.sumeng.peekshopping.constant.StatusCode;
import com.sumeng.peekshopping.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @date: 2020/6/30 13:10
 * @author: lenaminz
 */
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 根据小说ID查询分类
     * @param bookId
     * @return
     */
    @GetMapping("/findByBookId/{bookId}")
    public Result<Category> findByBookId(@PathVariable("bookId") String bookId){
        Category category = categoryService.findByBookId(bookId);
        return new Result<>(true, StatusCode.OK, "查询分类成功", category);
    }

    /**
     * 查询所有分类信息
     * @return
     */
    @GetMapping("/findAll")
    public Result<List<Category>> findAll(){
        List<Category> categoryList = categoryService.findAll();
        return new Result<>(true, StatusCode.OK, "查询分类成功", categoryList);
    }

    /**
     * 根据分类ID查询分类信息
     * @return
     */
    @GetMapping("/findByCategoryId/{id}")
    public Result<Category> findByCategoryId(@PathVariable("id") Integer id){
        Category category = categoryService.findByCategoryId(id);
        return new Result<>(true, StatusCode.OK, "查询分类成功", category);
    }

}
