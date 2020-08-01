package com.foxes.read.service.impl;

import com.foxes.read.dao.BookMapper;
import com.foxes.read.dao.CategoryMapper;
import com.foxes.read.pojo.Book;
import com.foxes.read.pojo.Category;
import com.foxes.read.pojo.Chapter;
import com.foxes.read.service.BookService;
import com.foxes.read.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import tk.mybatis.mapper.entity.Example;

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
    private ChapterService chapterService;


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
        //根据作者查询小说列表
        List<Book> bookListOfAuthor = this.findListByAuthor(book.getAuthor());
        //获取分类信息
        Category category = categoryMapper.findByBookId(bookId);
        //获取最新章节
        Chapter lastChapter = chapterService.findLastChapterByBookId(bookId);
        //获取所有章节
        List<Chapter> chapterList = chapterService.findAllChapterByBookId(bookId);

        dataMap.put("book",book); //小说详情数据
        dataMap.put("bookListOfAuthor",bookListOfAuthor); //某作者的所有小说列表
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

    /**
     * 查询阅读数前十的小说
     * @return
     */
    @Override
    public List<Book> findByReadNumDesc() {
        return bookMapper.findByReadNumDesc();
    }

    /**
     * 查询订阅数前十的小说
     * @return
     */
    @Override
    public List<Book> findBySubscribeNumDesc() {
        return bookMapper.findBySubscribeNumDesc();
    }

    /**
     * 根据作者查询小说列表
     * @param author
     * @return
     */
    @Override
    public List<Book> findListByAuthor(String author) {
        Example example = new Example(Book.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("author",author);
        return bookMapper.selectByExample(example);
    }


}
