package com.foxes.book.dao;

import com.foxes.book.pojo.Book;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @date: 2020/6/28 16:37
 * @author: sumeng
 */
@Repository
public interface BookMapper extends Mapper<Book> {

    /**
     * 查询阅读数前十的小说
     * @return
     */
    @Select("select * from tb_book order by read_num DESC limit 0,10")
    List<Book> findByReadNumDesc();

    /**
     * 查询订阅数前十的小说
     * @return
     */
    @Select("select * from tb_book order by subscribe_num DESC limit 0,10")
    List<Book> findBySubscribeNumDesc();

}

