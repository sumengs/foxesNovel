package com.foxes.book.dao;

import com.foxes.book.pojo.Category;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @date: 2020/6/30 9:27
 * @author: lenaminz
 */
@Repository
public interface CategoryMapper extends Mapper<Category> {

    /**
     * 根据小说ID查询分类
     * @param bookId
     * @return
     */
    @Select("select tc.* from tb_book tb,tb_category tc where tb.category = tc.id and tb.id = #{bookId}")
    Category findByBookId(@Param("bookId") String bookId);
}
