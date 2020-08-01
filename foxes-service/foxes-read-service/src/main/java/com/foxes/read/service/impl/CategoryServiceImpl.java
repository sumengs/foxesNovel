package com.foxes.read.service.impl;

import com.foxes.read.dao.CategoryMapper;
import com.foxes.read.pojo.Category;
import com.foxes.read.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @date: 2020/6/30 13:14
 * @author: lenaminz
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 根据小说ID查询分类
     * @param bookId
     * @return
     */
    @Override
    public Category findByBookId(String bookId) {
        return categoryMapper.findByBookId(bookId);
    }

    /**
     * 查询所有分类信息
     * @return
     */
    @Override
    public List<Category> findAll() {
        return categoryMapper.selectAll();
    }

    /**
     * 根据分类ID查询分类信息
     * @param categoryId
     * @return
     */
    @Override
    public Category findByCategoryId(Integer categoryId) {
        return categoryMapper.selectByPrimaryKey(categoryId);
    }


}
