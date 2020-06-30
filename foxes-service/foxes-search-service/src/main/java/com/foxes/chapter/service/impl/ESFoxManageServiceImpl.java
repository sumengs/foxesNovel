package com.foxes.chapter.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.foxes.book.feign.BookFeign;
import com.foxes.book.pojo.Book;
import com.foxes.chapter.dao.ESFoxManageMapper;
import com.foxes.chapter.service.ESFoxManageService;
import com.foxesnovel.search.pojo.BookInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;

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

    @Override
    public void createIndex() {
        elasticsearchRestTemplate.createIndex(BookInfo.class);
        elasticsearchRestTemplate.putMapping(BookInfo.class);
    }

    @Override
    public void importAll() {
        List<Book> all = bookFeign.findAll();
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
        List<BookInfo> rows = null;
        Map<Integer, String> cate = bookFeign.findCate();
        for (BookInfo book : books) {
            book.setCategoryName(cate.get(book.getCategory()));
            rows.add(book);
        }
        esFoxSearchMapper.saveAll(rows);
    }

    @Override
    public void importById(String bookId) {
        Book one = bookFeign.findOne(bookId);
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
