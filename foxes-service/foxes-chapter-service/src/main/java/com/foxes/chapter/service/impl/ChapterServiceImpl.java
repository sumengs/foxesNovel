package com.foxes.chapter.service.impl;

import com.foxes.book.feign.BookFeign;
import com.foxes.book.feign.CategoryFeign;
import com.foxes.book.pojo.Book;
import com.foxes.book.pojo.Category;
import com.foxes.chapter.dao.ChapterMapper;
import com.foxes.chapter.pojo.Chapter;
import com.foxes.chapter.service.ChapterService;
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
    private BookFeign bookFeign;


    @Autowired
    private CategoryFeign categoryFeign;

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
        criteria.andEqualTo("bookId",bookId);
        criteria.andEqualTo("next","0");

        Chapter chapter = chapterMapper.selectOneByExample(example);

        return chapter;
    }

    @Override
    public List<Chapter> findAllChapterByBookId(String bookId) {
        Example example = new Example(Chapter.class);

        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("bookId",bookId);


        return chapterMapper.selectByExample(example);
    }

    @Override
    public Chapter findFirstChapterByBookId(String bookId) {
        Example example = new Example(Chapter.class);

        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("bookId",bookId);
        criteria.andEqualTo("pre","0");

        Chapter chapter = chapterMapper.selectOneByExample(example);

        return chapter;
    }





    @Override
    public void generateHtml(String id) throws FileNotFoundException {
        //1. 获取context对象，用于存储商品的相关信息
        Context context = new Context();


        //获取静态页面相关数据
        Chapter chapter = this.findChapterById(id);

        Result<Book> book = bookFeign.findById(chapter.getBookId());


        Result<Category> category = categoryFeign.findByCategoryId(book.getData().getCategory());


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String bookReleaseTime = simpleDateFormat.format(book.getData().getReleaseTime());
        String chapterUpdateTime = simpleDateFormat.format(chapter.getUpdateTime());


        Map<String, Object> itemData = new HashMap<>();
        itemData.put("chapter", chapter);
        itemData.put("book", book.getData());
        itemData.put("bookReleaseTime", bookReleaseTime);
        itemData.put("chapterUpdateTime", chapterUpdateTime);
        itemData.put("category", category.getData());
        context.setVariables(itemData);

        String path = ResourceUtils.getURL("classpath:").getPath();


        //2. 获取商品详情页面位置
        File dir = new File(path + "/static/" + book.getData().getId());
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
