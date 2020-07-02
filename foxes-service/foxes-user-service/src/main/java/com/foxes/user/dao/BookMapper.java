package com.foxes.user.dao;

import com.foxes.user.pojo.Book;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;


public interface BookMapper extends Mapper<Book> {
    @Select("select name from tb_book where id =#{id}")
    String listBookName(String id);
}
