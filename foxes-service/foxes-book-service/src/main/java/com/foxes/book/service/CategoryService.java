package com.foxes.book.service;

import com.foxes.book.pojo.Category;

import java.util.List;

/**
 * @date: 2020/6/30 13:12
 * @author: lenaminz
 */
public interface CategoryService {

    /**
     * 根据小说ID查询分类
     * @param bookId
     * @return
     */
    Category findByBookId(String bookId);

    /**
     * 查询所有分类信息
     * @return
     */
    List<Category> findAll();

    /**
     * 根据分类ID查询分类信息
     * @param categoryId
     * @return
     */
    Category findByCategoryId(Integer categoryId);
}
