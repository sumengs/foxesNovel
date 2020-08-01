package com.foxes.read.feign;


import com.foxes.read.pojo.Chapter;
import com.sumeng.peekshopping.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @date: 2020/6/30 13:14
 * @author: sumeng
 */
@FeignClient(name = "chapter-service")
public interface ChapterFeign {

    /**
     * 根据章节Id查询章节信息
     *
     * @param chapterId 章节Id
     * @return 章节信息
     */
    @GetMapping("/chapter/findById/{id}")
    public Result<Chapter> findChapterById(@PathVariable("id") String chapterId);


    /**
     * 根据bookId查询所有章节
     *
     * @param chapterId 书籍Id
     * @return 所有章节
     */
    @GetMapping("/chapter/findAllChapterByBookId/{id}")
    public Result<List<Chapter>> findAllChapterByBookId(@PathVariable("id") String chapterId);


    /**
     * 根据bookId查询第一章节
     *
     * @param chapterId 书籍Id
     * @return 第一章节
     */
    @GetMapping("/chapter/findFirstChapterByBookId/{id}")
    public Result<Chapter> findFirstChapterByBookId(@PathVariable("id") String chapterId);

    /**
     * 根据bookId查询最新章节
     *
     * @param chapterId 书籍Id
     * @return 最新章节
     */
    @GetMapping("/chapter/findLastChapterByBookId/{id}")
    public Result<Chapter> findLastChapterByBookId(@PathVariable("id") String chapterId);
}
