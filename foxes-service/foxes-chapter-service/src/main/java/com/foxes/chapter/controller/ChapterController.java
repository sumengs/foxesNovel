package com.foxes.chapter.controller;

import com.foxes.chapter.pojo.Book;
import com.foxes.chapter.pojo.Chapter;
import com.foxes.chapter.service.ChapterService;
import com.sumeng.peekshopping.constant.StatusCode;
import com.sumeng.peekshopping.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @date: 2020/6/28 18:15
 * @author: sumeng
 */
@Controller
@RequestMapping("/chapter")
public class ChapterController {

    @Autowired
    private ChapterService chapterService;


    @GetMapping("/findById/{id}")
    @ResponseBody
    public Result<Chapter> findChapterById(@PathVariable("id") String chapterId) {
        Chapter chapter = chapterService.findChapterById(chapterId);
        return new Result<>(true, StatusCode.OK, "查询章节详情成功", chapter);
    }

}
