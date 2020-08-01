package com.foxes.read.service.impl;

import com.foxes.read.dao.ChapterMapper;
import com.foxes.read.feign.BookFeign;
import com.foxes.read.feign.CategoryFeign;
import com.foxes.read.pojo.Book;
import com.foxes.read.pojo.Category;
import com.foxes.read.pojo.Chapter;
import com.foxes.read.service.BookService;
import com.foxes.read.service.CategoryService;
import com.foxes.read.service.ChapterService;
import com.sumeng.peekshopping.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import tk.mybatis.mapper.entity.Example;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
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
    private BookService bookService;


    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TemplateEngine templateEngine;


    @Override
    public Chapter findChapterById(String id) {
        return chapterMapper.selectByPrimaryKey(id);
    }

    @Override
    public Chapter findLastChapterByBookId(String bookId) {
        Example example = new Example(Chapter.class);

        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("bookId", bookId);
        criteria.andEqualTo("next", "0");

        Chapter chapter = chapterMapper.selectOneByExample(example);

        return chapter;
    }

    /**
     * 查看所有章节名称
     * @param bookId 书籍Id
     * @return 章鱼名称
     */
    @Override
    public List<Chapter> findAllChapterByBookId(String bookId) {
        Example example = new Example(Chapter.class);

        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("bookId", bookId);


        return chapterMapper.selectByExample(example);
    }

    @Override
    public Chapter findFirstChapterByBookId(String bookId) {
        Example example = new Example(Chapter.class);

        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("bookId", bookId);
        criteria.andEqualTo("pre", "0");

        Chapter chapter = chapterMapper.selectOneByExample(example);

        return chapter;
    }


    /**
     * 生成页面
     *
     * @param id 章节Id
     */
    @Override
    public void generateHtml(String id) {
        //1. 获取context对象，用于存储商品的相关信息
        Context context = new Context();


        //获取静态页面相关数据
        Chapter chapter = this.findChapterById(id);

        //判断网页是否被删除
        if (chapter.getIsDelete() == 1) {
            return;
        }


        //判断网页是否过审
        if (chapter.getIsVerify() == 0) {
            return;
        }

        Book book = bookService.findBookById(chapter.getBookId());


        Category category = categoryService.findByCategoryId(book.getCategory());


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

        String path = null;
        try {
            path = ResourceUtils.getURL("classpath:").getPath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


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

    @Override
    public void deleteHtml(String id) {
        Chapter chapter = chapterMapper.selectByPrimaryKey(id);
        String path = null;
        try {
            path = ResourceUtils.getURL("classpath:").getPath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String fileName = path + "/static/" + chapter.getBookId() + "/" + id + ".html";

        File file = new File(fileName);
        if (file.exists()) {
            boolean delete = file.delete();
        }
    }

    @Override
    public List<Chapter> findAll() {
        return chapterMapper.selectAll();
    }


}
