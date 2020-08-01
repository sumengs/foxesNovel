package com.foxes.read.controller;



import com.foxes.read.pojo.Chapter;
import com.foxes.read.service.ChapterService;
import com.sumeng.peekshopping.constant.StatusCode;
import com.sumeng.peekshopping.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @date: 2020/6/28 18:15
 * @author: sumeng
 */
@Controller
@CrossOrigin
@RequestMapping("/chapter")
public class ChapterController {

    @Autowired
    private ChapterService chapterService;


    /**
     * 查询所有章节详情成功
     * @param bookId 书籍Id
     * @return chapterList
     */
    @GetMapping("/findAllChapterByBookId/{id}")
    @ResponseBody
    public Result<List<Chapter>> findAllChapterByBookId(@PathVariable("id") String bookId) {
        List<Chapter> chapterList = chapterService.findAllChapterByBookId(bookId);
        return new Result<>(true, StatusCode.OK, "查询所有章节详情成功", chapterList);
    }


    @GetMapping("/findFirstChapterByBookId/{id}")
    @ResponseBody
    public Result<Chapter> findFirstChapterByBookId(@PathVariable("id") String chapterId) {
        Chapter chapter = chapterService.findFirstChapterByBookId(chapterId);
        return new Result<>(true, StatusCode.OK, "查询第一章节详情成功", chapter);
    }

    @GetMapping("/findLastChapterByBookId/{id}")
    @ResponseBody
    public Result<Chapter> findLastChapterByBookId(@PathVariable("id") String chapterId) {
        Chapter chapter = chapterService.findLastChapterByBookId(chapterId);
        return new Result<>(true, StatusCode.OK, "查询最新章节详情成功", chapter);
    }


    @GetMapping("/findById/{id}")
    @ResponseBody
    public Result<Chapter> findChapterById(@PathVariable("id") String chapterId) {
        Chapter chapter = chapterService.findChapterById(chapterId);
        return new Result<>(true, StatusCode.OK, "查询章节详情成功", chapter);
    }

    @GetMapping("/generate/{id}")
    @ResponseBody
    public List<Chapter> generate(@PathVariable("id") String id) {

        List<Chapter> chapterList = chapterService.findAllChapterByBookId(id);

        for (Chapter chapter : chapterList) {
            try {
                chapterService.generateHtml(chapter.getId());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return chapterList;
    }


    @GetMapping("/generate/all")
    @ResponseBody
    public List<Chapter> generateAll() {

        List<Chapter> chapterList = chapterService.findAll();

        for (Chapter chapter : chapterList) {
            try {
                chapterService.generateHtml(chapter.getId());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return chapterList;
    }
}


