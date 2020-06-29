package com.foxes.book.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.foxes.book.dao.CategoryMapper;
import com.foxes.book.service.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService{

    @Resource
    private CategoryMapper categoryMapper;

}
