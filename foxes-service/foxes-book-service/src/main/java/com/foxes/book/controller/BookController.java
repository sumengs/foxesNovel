package com.foxes.book.controller;

import com.foxes.book.pojo.Book;
import com.foxes.book.service.BookService;
import com.foxes.book.service.CategoryService;
import com.foxes.chapter.feign.ChapterFeign;
import com.sumeng.peekshopping.constant.StatusCode;
import com.sumeng.peekshopping.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @date: 2020/6/28 16:37
 * @author: sumeng
 */

@RestController
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
    @GetMapping("/findById/{id}")
    public Result<Book> findById(@PathVariable("id") String bookId) {
        Book book = bookService.findBookById(bookId);
        return new Result<>(true, StatusCode.OK, "查询书籍详情成功", book);
    }

    /**
     * 查询所有Book数据
     * @return
     */
    @GetMapping("/findAll")
    public Result<List<Book>> findAll() {
        List<Book> bookList = bookService.findAll();
        return new Result<>(true, StatusCode.OK, "查询所有书籍成功", bookList);
    }

    /**
     * 查询阅读数前十的小说
     * @return
     */
    @GetMapping("/findTopTenRead")
    public Result<List<Book>> findTopTenRead() {
        List<Book> bookList = bookService.findByReadNumDesc();
        return new Result<>(true, StatusCode.OK, "查询阅读数排行前十书籍成功", bookList);
    }

    /**
     * 查询阅读数前十的小说
     * @return
     */
    @GetMapping("/findTopTenSubscribe")
    public Result<List<Book>> findTopTenSubscribe() {
        List<Book> bookList = bookService.findBySubscribeNumDesc();
        return new Result<>(true, StatusCode.OK, "查询订阅数排行前十书籍成功", bookList);
    }

    /**
     * 跳转小说详情页
     * @param bookId
     * @param model
     * @return
     */
    @RequestMapping("/generateBookHtml")
    public String toBook(@RequestParam("bookId") String bookId, Model model){
        bookService.generateHtml(bookId);
        return "success";
    }

}
