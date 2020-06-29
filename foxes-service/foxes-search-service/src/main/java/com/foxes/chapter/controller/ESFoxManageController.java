package com.foxes.chapter.controller;

import com.foxes.chapter.service.ESFoxManageService;
import com.sumeng.peekshopping.constant.StatusCode;
import com.sumeng.peekshopping.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/esmanage")
public class ESFoxManageController {

    @Autowired
    private ESFoxManageService esFoxManageService;

    @RequestMapping("/creat")
    public Result creat(){
        try {
            esFoxManageService.createIndex();
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, StatusCode.ERROR,"创建索引失败");
        }
        return new Result(true, StatusCode.OK,"创建索引成功");
    }

    @RequestMapping("/delete")
    public Result delete(String bookId){
        try {
            esFoxManageService.deleteById(bookId);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, StatusCode.ERROR,"删除书籍失败");
        }
        return new Result(true, StatusCode.OK,"删除书籍成功");
    }

    @RequestMapping("/importall")
    public Result saveAll(){
        try {
            esFoxManageService.importAll();
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, StatusCode.ERROR,"导入书籍失败");
        }
        return new Result(true, StatusCode.OK,"导入书籍成功");
    }

    @RequestMapping("/importone/{bookId}")
    public Result saveById(@PathVariable("bookId") String bookId){
        try {
            esFoxManageService.importById(bookId);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, StatusCode.ERROR,"导入书籍失败");
        }
        return new Result(true, StatusCode.OK,"导入书籍成功");
    }
}
