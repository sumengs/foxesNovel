package com.foxes.chapter.service;

import com.foxes.chapter.pojo.Chapter;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * @date: 2020/6/28 18:15
 * @author: sumeng
 */

public interface ChapterService {

    /**
     * 根据章节Id查询小说章节详情
     *
     * @param id 章节Id
     * @return 小说
     */
    Chapter findChapterById(String id);


    /**
     * 根据bookId查询最新章节
     *
     * @param bookId 书籍Id
     * @return 最新章节
     */
    Chapter findLastChapterByBookId(String bookId);


    /**
     * 根据bookId查询所有章节
     *
     * @param bookId 书籍Id
     * @return 所有章节
     */
    List<Chapter> findAllChapterByBookId(String bookId);


    /**
     * 根据bookId查询第一章节
     *
     * @param bookId 书籍Id
     * @return 第一章节
     */
    Chapter findFirstChapterByBookId(String bookId);


    /**
     * 生成静态页面
     *
     * @param id
     * @throws FileNotFoundException
     */
    void generateHtml(String id) throws FileNotFoundException;
}
