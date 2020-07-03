package com.foxes.user.service.impl;

import com.foxes.user.config.TokenDecode;
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
    @Autowired
    private TokenDecode tokenDecode;
    private static final String SET_KEY="setKey-";
    private static final String List_KEY="listKey-";

    //返回书架中的书(Book)
    @Override
    public List<Book> list() {
       // String username = tokenDecode.getUserInfo().get("username");
        String username = "zhangsan";

        Set<String> members = redisTemplate.opsForSet().members(SET_KEY+username);
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
        redisTemplate.opsForSet().add(SET_KEY+username, bookId);
    }

    //取消书架
    @Override
    public void delete(String bookId) {
        String username = "zhangsan";
        redisTemplate.opsForSet().remove(SET_KEY+username, bookId);

    }

    //最近阅读的小说id添加到redis中
    @Override
    public void addTime(String bookId) {
        String username = "zhangsan";
        Long size = redisTemplate.opsForList().size(List_KEY+username);
        if (size > 2) {
            redisTemplate.opsForList().rightPop(List_KEY+username);
        }
        redisTemplate.opsForList().leftPush(List_KEY+username, bookId);
    }

    //查询最近阅读
    @Override
    public List<Book> listFavourite() {
        String username = "zhangsan";
        List<String> range = redisTemplate.opsForList().range(List_KEY+username, 0, redisTemplate.opsForList().size(List_KEY+username));
        List<Book> bookList = new ArrayList<>();
        for (String book : range) {
            Book book1 = bookMapper.selectByPrimaryKey(book);
            bookList.add(book1);
        }
        return bookList;
    }

}
