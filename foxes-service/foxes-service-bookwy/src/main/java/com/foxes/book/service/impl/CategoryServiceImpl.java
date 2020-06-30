package com.foxes.book.service.impl;

import com.foxes.book.pojo.Category;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.foxes.book.dao.CategoryMapper;
import com.foxes.book.service.CategoryService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public Map<Integer,String> find() {
        Map<Integer,String> map=new HashMap<>();
        List<Category> categories = categoryMapper.selectAll();
        for (Category category : categories) {
            Integer id = category.getId();
            String name = category.getName();
            map.put(id,name);
        }
        return map;

    }
}
