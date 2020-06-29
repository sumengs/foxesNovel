package com.foxes.chapter.service;

import com.foxesnovel.search.pojo.BookInfo;

import java.util.List;
import java.util.Map;

public interface ESBookSearchService {
    /**
     * 关键字查询
     * @param map 需要查询的字段集合
     * @return 查询到的书本信息
     */
    Map search(Map<String,String> map);


}
