package com.foxes.chapter.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.foxes.read.feign.BookFeign;
import com.foxes.read.feign.CategoryFeign;
import com.foxes.read.pojo.Book;
import com.foxes.read.pojo.Category;
import com.foxes.chapter.dao.ESFoxManageMapper;
import com.foxes.chapter.service.ESFoxManageService;
import com.foxesnovel.search.pojo.BookInfo;
import com.sumeng.peekshopping.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ESFoxManageServiceImpl implements ESFoxManageService {


    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Autowired
    private ESFoxManageMapper esFoxSearchMapper;

    @Autowired
    private BookFeign bookFeign;
    @Autowired
    private CategoryFeign categoryFeign;

    @Override
    public void createIndex() {
        elasticsearchRestTemplate.createIndex(BookInfo.class);
        elasticsearchRestTemplate.putMapping(BookInfo.class);
    }

    @Override
    public void importAll() {
        Result all1 = bookFeign.findAll();
        List<Book> all = (List<Book>)all1.getData();
        if (all == null || all.size() <= 0) {
            throw new RuntimeException("当前书籍不存在");
        }
        ObjectMapper om = new ObjectMapper();
        List<BookInfo> books = null;
        try {
            String s = om.writeValueAsString(all);
            books = om.readValue(s, new TypeReference<List<BookInfo>>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("json字符串转化失败");
        }
        List<BookInfo> rows = new ArrayList<>();
        Result all2 = categoryFeign.findAll();
        List<Category> data = (List<Category>)all2.getData();

        Map<Integer,String> cate=new HashMap<>();

        for (Category category : data) {
            Integer id = category.getId();
            String name = category.getName();
            cate.put(id,name);
        }

        for (BookInfo book : books) {
            book.setCategoryName(cate.get(book.getCategory()));
            rows.add(book);
        }
        esFoxSearchMapper.saveAll(rows);
    }

    @Override
    public void importById(String bookId) {
        Result<Book> byId = bookFeign.findById(bookId);

        Book one = (Book) byId.getData();
        if (one == null) {
            throw new RuntimeException("当前书籍不存在");
        }
        ObjectMapper om = new ObjectMapper();
        BookInfo bookInfo = null;
        try {
            String s = om.writeValueAsString(one);
            bookInfo = om.readValue(s, new TypeReference<BookInfo>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("json字符串转化失败");
        }

        esFoxSearchMapper.save(bookInfo);
    }

    @Override
    public void deleteById(String bookId) {
        esFoxSearchMapper.deleteById(Long.parseLong(bookId));
    }
}
