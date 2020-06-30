package com.foxes.chapter.controller;


import com.foxes.chapter.pojo.Book;
import com.foxes.chapter.pojo.Category;
import com.foxes.chapter.pojo.Chapter;
import com.foxes.chapter.service.ChapterService;
import com.sumeng.peekshopping.constant.StatusCode;
import com.sumeng.peekshopping.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @date: 2020/6/28 18:15
 * @author: sumeng
 */
@Controller
@RequestMapping("/chapter")
public class ChapterController {

    @Autowired
    private ChapterService chapterService;


    @GetMapping("/findAllChapterByBookId/{id}")
    @ResponseBody
    public Result<List<Chapter>> findAllChapterByBookId(@PathVariable("id") String chapterId) {
        List<Chapter> chapterList = chapterService.findAllChapterByBookId(chapterId);
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


    @GetMapping("/test")
    public String test(Model model, String id) {


        Chapter chapter = chapterService.findChapterById(id);
        Book book = chapterService.findBookById(chapter.getBookId());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String bookReleaseTime = simpleDateFormat.format(book.getReleaseTime());
        String chapterUpdateTime = simpleDateFormat.format(chapter.getUpdateTime());
        Category category = chapterService.findCategoryById(book.getCategory());


        model.addAttribute("chapter", chapter);
        model.addAttribute("book", book);
        model.addAttribute("bookReleaseTime", bookReleaseTime);
        model.addAttribute("chapterUpdateTime", chapterUpdateTime);
        model.addAttribute("category", category);
        return "chapter";
    }


    @GetMapping("/generate/{id}")
    @ResponseBody
    public String generate(@PathVariable("id") String id) {
        try {
            chapterService.generateHtml(id);
            return "true";
        } catch (Exception e) {
            e.printStackTrace();
            return "false";
        }

    }
}
