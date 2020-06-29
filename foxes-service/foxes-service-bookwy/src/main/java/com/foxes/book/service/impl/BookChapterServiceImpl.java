package com.foxes.book.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.foxes.book.dao.BookChapterMapper;
import com.foxes.book.service.BookChapterService;
@Service
public class BookChapterServiceImpl implements BookChapterService{

    @Resource
    private BookChapterMapper bookChapterMapper;

}
