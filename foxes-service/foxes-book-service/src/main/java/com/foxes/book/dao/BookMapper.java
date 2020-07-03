package com.foxes.book.dao;

import com.foxes.book.pojo.Book;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
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
    @Results({@Result(column = "name",property = "name"),
            @Result(column = "author",property = "author"),
            @Result(column = "abstracts",property = "abstracts"),
            @Result(column = "release_time",property = "releaseTime"),
            @Result(column = "update_time",property = "updateTime"),
            @Result(column = "channel",property = "channel"),
            @Result(column = "status",property = "status"),
            @Result(column = "is_free",property = "isFree"),
            @Result(column = "subscribe_num",property = "subscribeNum"),
            @Result(column = "words_num",property = "wordsNum"),
            @Result(column = "image",property = "image"),
            @Result(column = "is_delete",property = "isDelete"),
            @Result(column = "html",property = "html"),
            @Result(column = "channel",property = "channel"),
            @Result(column = "is_verify",property = "isVerify"),
            @Result(column = "read_num",property = "readNum"),
            @Result(column = "author_head",property = "authorHead")
    })
    List<Book> findByReadNumDesc();

    /**
     * 查询订阅数前十的小说
     * @return
     */
    @Select("select * from tb_book order by subscribe_num DESC limit 0,15")
    @Results({@Result(column = "name",property = "name"),
            @Result(column = "author",property = "author"),
            @Result(column = "abstracts",property = "abstracts"),
            @Result(column = "release_time",property = "releaseTime"),
            @Result(column = "update_time",property = "updateTime"),
            @Result(column = "channel",property = "channel"),
            @Result(column = "status",property = "status"),
            @Result(column = "is_free",property = "isFree"),
            @Result(column = "subscribe_num",property = "subscribeNum"),
            @Result(column = "words_num",property = "wordsNum"),
            @Result(column = "image",property = "image"),
            @Result(column = "is_delete",property = "isDelete"),
            @Result(column = "html",property = "html"),
            @Result(column = "channel",property = "channel"),
            @Result(column = "is_verify",property = "isVerify"),
            @Result(column = "read_num",property = "readNum"),
            @Result(column = "author_head",property = "authorHead")
    })
    List<Book> findBySubscribeNumDesc();

}

