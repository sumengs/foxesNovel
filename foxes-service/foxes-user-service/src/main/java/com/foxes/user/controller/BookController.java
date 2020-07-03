package com.foxes.user.controller;

import com.foxes.user.service.BookService;
import com.foxes.user.pojo.Book;
import com.sumeng.peekshopping.constant.StatusCode;
import com.sumeng.peekshopping.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookshelf")
public class BookController {

    @Autowired
    private BookService bookService;


    @GetMapping("/list")
    public Result list() {
        List<Book> bookList = bookService.list();
        if (bookList == null || bookList.size() < 0) {
            return new Result(false, StatusCode.ERROR, "查询失败");
        }
        return new Result(true, StatusCode.OK, "查询成功", bookList);
    }


    @GetMapping("/add/{bookId}")
    public Result add(@PathVariable("bookId") String bookId) {
        bookService.add(bookId);
        return new Result(true, StatusCode.OK, "添加书架成功");
    }

    @DeleteMapping("/delete/{bookId}")
    public Result delete(@PathVariable("bookId") String bookId) {
        bookService.delete(bookId);
        return new Result(true, StatusCode.OK, "取消书架成功");
    }


    @GetMapping("/addTime/{bookId}")
    public Result addTime(@PathVariable("bookId") String bookId) {
        bookService.addTime(bookId);
        return new Result(true, StatusCode.OK, "最近添加成功");
    }


    @GetMapping("/findFavourite")
    public Result findFavourite() {
        List<Book> bookList = bookService.listFavourite();
        return new Result(true, StatusCode.OK, "查询最近成功", bookList);
    }

}
