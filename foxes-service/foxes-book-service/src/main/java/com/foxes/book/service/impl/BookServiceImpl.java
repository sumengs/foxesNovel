package com.foxes.book.service.impl;

import com.foxes.book.dao.BookMapper;
import com.foxes.book.dao.CategoryMapper;
import com.foxes.book.pojo.Book;
import com.foxes.book.pojo.Category;
import com.foxes.book.service.BookService;
import com.foxes.chapter.feign.ChapterFeign;
import com.foxes.chapter.pojo.Chapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @date: 2020/6/28 16:38
 * @author: sumeng
 */
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private TemplateEngine templateEngine;
    @Autowired
    private ChapterFeign chapterFeign;


    /**
     * 生成静态化页面
     * @param bookId
     */
    @Override
    public void generateHtml(String bookId) {
        //创建context对象
        Context context = new Context();
        Map<String, Object> dataMap = new HashMap<>();
        //获取小说详情相关数据
        Book book = this.findBookById(bookId);
        //获取分类信息
        Category category = categoryMapper.findByBookId(bookId);
        //获取最新章节
        Chapter lastChapter = chapterFeign.findLastChapterByBookId(bookId).getData();
        //获取所有章节
        List<Chapter> chapterList = chapterFeign.findAllChapterByBookId(bookId).getData();

        dataMap.put("book",book); //小说详情数据
        dataMap.put("category",category); //章节数据
        dataMap.put("firstChapter",chapterList.get(0)); //第一章数据
        dataMap.put("lastChapter",lastChapter); //最后一章数据
        dataMap.put("chapterList",chapterList); //所有章节数据
        context.setVariables(dataMap);

        //定义静态详情页的存放位置
        String path = null;
        try {
            //获取classpath的绝对路径
            path = ResourceUtils.getURL("classpath:").getPath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        path = path + "\\static\\book";
        File dir = new File(path);
        if(!dir.exists()){
            dir.mkdirs();
        }
        //定义输出流，完成页面文件的生成
        File file = new File(dir + "\\" + bookId + ".html");
        Writer out = null;
        try{
            out = new PrintWriter(file);
            //生成静态化页面
            /**
             * 1.模板名称
             * 2.context
             * 3.输出流
             */
            templateEngine.process("book",context,out);
            System.out.println("生成静态页面成功");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //关闭流
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 删除静态化页面
     * @param bookId
     */
    @Override
    public void deleteHtml(String bookId) {
        String path = null;
        try {
            path = ResourceUtils.getURL("classpath:").getPath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String fileName = path + "/static/book/" + bookId + ".html";

        File file = new File(fileName);
        if (file.exists()) {
            file.delete();
            System.out.println("删除静态页面成功");
        }
    }

    /**
     * 根据id查询小说数据
     * @param bookId bookID
     * @return
     */
    @Override
    public Book findBookById(String bookId) {
        return bookMapper.selectByPrimaryKey(bookId);
    }

    /**
     * 查询所有Book数据
     * @return
     */
    @Override
    public List<Book> findAll() {
        return bookMapper.selectAll();
    }


}
