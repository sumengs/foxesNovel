package com.foxes.user.service.impl;

import com.foxes.user.dao.BookMapper;
import com.foxes.user.service.BookService;
import com.foxes.user.pojo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private BookMapper bookMapper;

    //返回书架中的书(Book)
    @Override
    public List<Book> list() {
        String username = "zhangsan";
        Set<String> members = redisTemplate.opsForSet().members(username);
        List<Book> bookList = new ArrayList<>();

        if (members != null && members.size() > 0) {
            for (String member : members) {
                Book book = bookMapper.selectByPrimaryKey(member);
                bookList.add(book);
            }
        }
        return bookList;
    }




    //添加书架
    @Override
    public void add(String bookId) {
        String username = "zhangsan";
        redisTemplate.opsForSet().add(username, bookId);
    }

    //取消书架
    @Override
    public void delete(String bookId) {
        String username = "zhangsan";
        redisTemplate.opsForSet().remove(username, bookId);

    }

    //最近阅读的小说id添加到redis中
    @Override
    public void addTime(String bookId) {
        String username = "zhangsan";
        Long size = redisTemplate.opsForList().size(username);
        if (size > 2) {
            redisTemplate.opsForList().rightPop(username);
        }
        redisTemplate.opsForList().leftPush(username, bookId);
    }

    //查询最近阅读
    @Override
    public List<Book> listFavourite(String bookId) {
        String username = "zhangsan";
        List<String> range = redisTemplate.opsForList().range(username, 0, redisTemplate.opsForList().size(username));
        List<Book> bookList = new ArrayList<>();
        for (String book : range) {
            Book book1 = bookMapper.selectByPrimaryKey(book);
            bookList.add(book1);
        }
        return bookList;
    }

}
