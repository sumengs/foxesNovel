package com.foxes.book.dao;

import com.foxes.book.pojo.Book;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @date: 2020/6/28 16:37
 * @author: sumeng
 */
@Repository
public interface BookMapper extends Mapper<Book> {
}