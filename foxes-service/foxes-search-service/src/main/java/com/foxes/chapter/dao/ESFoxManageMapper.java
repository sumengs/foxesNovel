package com.foxes.chapter.dao;

import com.foxesnovel.search.pojo.BookInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ESFoxManageMapper extends ElasticsearchRepository<BookInfo,Long> {
}
