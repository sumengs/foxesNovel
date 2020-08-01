package com.foxes.read.controller;

import com.foxes.read.pojo.Book;
import com.foxes.read.service.BookService;
import com.foxes.read.service.CategoryService;
import com.foxes.read.service.ChapterService;
import com.sumeng.peekshopping.constant.StatusCode;
import com.sumeng.peekshopping.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
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
    private ChapterService chapterService;

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
     * 生成小说详情页
     * @param bookId
     * @return
     */
    @RequestMapping("/generateHtml/{id}")
    public String generateHtml(@PathVariable("id") String bookId){
        bookService.generateHtml(bookId);
        return "success";
    }


    @RequestMapping("/generateAllBook")
    public String generateAllBook(){
        List<Book> bookList = bookService.findAll();
        for (Book book : bookList) {
            String id = book.getId();
            bookService.generateHtml(id);
        }
        return "success";
    }

}
