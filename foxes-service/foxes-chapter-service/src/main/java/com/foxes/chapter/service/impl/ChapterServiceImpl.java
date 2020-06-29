package com.foxes.chapter.service.impl;

import com.foxes.chapter.dao.BookMapper;
import com.foxes.chapter.dao.ChapterMapper;
import com.foxes.chapter.pojo.Chapter;
import com.foxes.chapter.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @date: 2020/6/28 18:16
 * @author: sumeng
 */
@Service
public class ChapterServiceImpl implements ChapterService {


    @Autowired
    private ChapterMapper chapterMapper;


    @Autowired
    private BookMapper bookMapper;

    @Override
    public Chapter findChapterById(String id) {
        return chapterMapper.selectByPrimaryKey(id);
    }
}
