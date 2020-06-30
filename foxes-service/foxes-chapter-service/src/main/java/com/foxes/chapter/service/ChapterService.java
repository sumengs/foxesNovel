package com.foxes.chapter.service;

import com.foxes.chapter.pojo.Book;
import com.foxes.chapter.pojo.Category;
import com.foxes.chapter.pojo.Chapter;

import java.io.FileNotFoundException;

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
     * 根据ID查询小说详情
     * @param id bookId
     * @return book
     */
    Book findBookById(String id);


    /**
     * 根据分类Id查询分类信息
     * @param id id
     * @return 分类信息
     */
    Category findCategoryById(int id);



    /**
     * 生成静态页面
     * @param id 章节Id
     */
    void generateHtml(String id) throws FileNotFoundException;
}
