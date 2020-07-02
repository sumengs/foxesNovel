package com.foxes.book.controller;

import com.foxes.book.pojo.Book;
import com.foxes.book.pojo.Category;
import com.foxes.book.service.BookService;
import com.foxes.book.service.CategoryService;
import com.foxes.chapter.feign.ChapterFeign;
import com.foxes.chapter.pojo.Chapter;
import com.sumeng.peekshopping.constant.StatusCode;
import com.sumeng.peekshopping.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @date: 2020/6/28 16:37
 * @author: sumeng
 */

@Controller
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ChapterFeign chapterFeign;

    /**
     * 根据id查询小说数据
     * @param bookId
     * @return
     */
    @ResponseBody
    @GetMapping("/findById/{id}")
    public Result<Book> findById(@PathVariable("id") String bookId) {
        Book book = bookService.findBookById(bookId);
        return new Result<>(true, StatusCode.OK, "查询书籍详情成功", book);
    }

    /**
     * 查询所有Book数据
     * @return
     */
    @ResponseBody
    @GetMapping("/findAll")
    public Result<List<Book>> findAll() {
        List<Book> bookList = bookService.findAll();
        return new Result<>(true, StatusCode.OK, "查询所有书籍成功", bookList);
    }

    /**
     * 跳转小说详情页
     * @param bookId
     * @param model
     * @return
     */
    @RequestMapping("/toBook")
    public String toBook(@RequestParam("bookId") String bookId, Model model){
        Book book = this.findById(bookId).getData();
        //查询分类信息
        Category category = categoryService.findByBookId(bookId);
        //查询最新章节
        Chapter lastChapter = chapterFeign.findLastChapterByBookId(bookId).getData();
        //查询所有章节
        List<Chapter> chapterList = chapterFeign.findAllChapterByBookId(bookId).getData();

        model.addAttribute("firstChapter",chapterList.get(0));
        model.addAttribute("chapterList",chapterList);
        model.addAttribute("lastChapter",lastChapter);
        model.addAttribute("category",category);
        model.addAttribute("book",book);

        return "book";
    }


}
