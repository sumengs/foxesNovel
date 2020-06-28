package com.foxes.chapter.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.foxes.chapter.dao.ESFoxManageMapper;
import com.foxes.chapter.service.ESFoxManageService;
import com.foxesnovel.search.pojo.Book;
import com.foxesnovel.search.pojo.BookInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ESFoxManageServiceImpl implements ESFoxManageService {


    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;
    @Autowired
    private ESFoxManageMapper esFoxSearchMapper;

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


        esFoxSearchMapper.saveAll(books);
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
