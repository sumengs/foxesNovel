package com.foxes.chapter.service;

import com.foxes.chapter.pojo.Chapter;

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
}
