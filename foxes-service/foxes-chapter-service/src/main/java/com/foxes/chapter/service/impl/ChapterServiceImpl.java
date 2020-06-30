package com.foxes.chapter.service.impl;

import com.foxes.chapter.dao.BookMapper;
import com.foxes.chapter.dao.CategoryMapper;
import com.foxes.chapter.dao.ChapterMapper;
import com.foxes.chapter.pojo.Book;
import com.foxes.chapter.pojo.Category;
import com.foxes.chapter.pojo.Chapter;
import com.foxes.chapter.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @date: 2020/6/28 18:16
 * @author: sumeng
 */
@Service
public class ChapterServiceImpl implements ChapterService {


    @Autowired
    private ChapterMapper chapterMapper;


    @Autowired
    private BookMapper bookMapper;


    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private TemplateEngine templateEngine;

    @Override
    public Chapter findChapterById(String id) {
        return chapterMapper.selectByPrimaryKey(id);
    }

    @Override
    public Book findBookById(String id) {
        return bookMapper.selectByPrimaryKey(id);
    }

    @Override
    public Category findCategoryById(int id) {
        return categoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public void generateHtml(String id) throws FileNotFoundException {
        //1. 获取context对象，用于存储商品的相关信息
        Context context = new Context();


        //获取静态页面相关数据
        Chapter chapter = this.findChapterById(id);

        Book book = this.findBookById(chapter.getBookId());

        Category category = this.findCategoryById(book.getCategory());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String bookReleaseTime = simpleDateFormat.format(book.getReleaseTime());
        String chapterUpdateTime = simpleDateFormat.format(chapter.getUpdateTime());


        Map<String, Object> itemData = new HashMap<>();
        itemData.put("chapter", chapter);
        itemData.put("book", book);
        itemData.put("bookReleaseTime", bookReleaseTime);
        itemData.put("chapterUpdateTime", chapterUpdateTime);
        itemData.put("category", category);
        context.setVariables(itemData);

        String path = ResourceUtils.getURL("classpath:").getPath();


        //2. 获取商品详情页面位置
        File dir = new File(path + "/static/" + book.getId());
        if (!dir.exists()) {
            dir.mkdirs();
        }

        //定义输出流

        File file = new File(dir + "/" + id + ".html");
        Writer writer = null;

        try {
            writer = new PrintWriter(file);
            templateEngine.process("chapter", context, writer);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


}
