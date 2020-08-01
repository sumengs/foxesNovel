package com.foxes.read.dao;


import com.foxes.read.pojo.Chapter;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @date: 2020/6/28 18:16
 * @author: sumeng
 */
public interface ChapterMapper extends Mapper<Chapter> {

}
