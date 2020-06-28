package com.foxes.book.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.foxes.book.dao.BookMapper;
import com.foxes.book.service.BookService;
@Service
public class BookServiceImpl implements BookService{

    @Resource
    private BookMapper bookMapper;

}
