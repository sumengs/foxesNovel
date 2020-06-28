package com.foxes.book.controller;

import com.foxes.book.pojo.Book;
import com.foxes.book.service.BookService;
import com.sumeng.peekshopping.constant.StatusCode;
import com.sumeng.peekshopping.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @date: 2020/6/28 16:37
 * @author: sumeng
 */

@Controller
@RequestMapping("/book")
public class BookController {


    @Autowired
    private BookService bookService;


    @ResponseBody
    @GetMapping("/findById/{id}")
    public Result<Book> findBookById(@PathVariable("id") String bookId) {

        Book book = bookService.findBookById(bookId);

        return new Result<>(true, StatusCode.OK, "查询书籍详情成功", book);
    }


}
