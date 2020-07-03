package com.foxes.homepage.service.impl;

import com.foxes.book.feign.BookFeign;
import com.foxes.book.feign.CategoryFeign;
import com.foxes.book.pojo.Book;
import com.foxes.book.pojo.Category;
import com.foxes.chapter.pojo.Leaderboard;
import com.foxes.homepage.dao.AdvertisementMapper;
import com.foxes.chapter.pojo.Advertisement;
import com.foxes.homepage.service.AdvertisementService;
import com.sumeng.peekshopping.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @date: 2020/7/2 13:56
 * @author: sumeng
 */
@Service
public class AdvertisementServiceImpl implements AdvertisementService {

    @Autowired
    private AdvertisementMapper advertisementMapper;


    @Autowired
    private BookFeign bookFeign;

    @Autowired
    private CategoryFeign categoryFeign;


    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<Advertisement> carousel() {

        Long num = redisTemplate.opsForList().size("carouselImage");
        return (List<Advertisement>) redisTemplate.opsForList().range("carouselImage", 0, num - 1);
    }

    @Override
    public Advertisement bigImage() {
        Advertisement advertisement = new Advertisement();
        String url = redisTemplate.opsForHash().get("bigImage", "url").toString();
        String image = redisTemplate.opsForHash().get("bigImage", "image").toString();
        advertisement.setUrl(url);
        advertisement.setImage(image);
        return advertisement;
    }

    @Override
    public List<Advertisement> smallImage() {
        Long num = redisTemplate.opsForList().size("smallImage");
        return (List<Advertisement>) redisTemplate.opsForList().range("smallImage", 0, num - 1);
    }

    @Override
    public Advertisement weixinImage() {
        String image = redisTemplate.opsForValue().get("wxImage").toString();
        Advertisement advertisement = new Advertisement();
        advertisement.setImage(image);
        return advertisement;
    }

    @Override
    public List<Leaderboard> subscribeList() {

        return (List<Leaderboard>) redisTemplate.opsForList().range("subscribeList", 0, redisTemplate.opsForList().size("subscribeList") - 1);
    }

    @Override
    public List<Leaderboard> readList() {
        return (List<Leaderboard>) redisTemplate.opsForList().range("readList", 0, redisTemplate.opsForList().size("readList") - 1);
    }

    @Override
    public void updateRedis() {
        updateBigImage();
        updateSmallImage();
        updateCarousel();
        updateWxImage();

        updateSubscribeList();
        updateReadList();


    }

    private void updateReadList() {
        Result<List<Book>> bookListResult = bookFeign.findTopTenRead();
        List<Leaderboard> leaderBoardList = packageLeaderBoardList(bookListResult);
        if (leaderBoardList != null || leaderBoardList.size() > 0) {
            redisTemplate.delete("readList");
            redisTemplate.opsForList().rightPushAll("readList", leaderBoardList);
        }
    }


    private void updateSubscribeList() {
        Result<List<Book>> bookListResult = bookFeign.findTopTenSubscribe();
        List<Leaderboard> leaderBoardList = packageLeaderBoardList(bookListResult);


        if (leaderBoardList != null || leaderBoardList.size() > 0) {
            redisTemplate.delete("subscribeList");
            redisTemplate.opsForList().rightPushAll("subscribeList", leaderBoardList);
        }
    }

    private List<Leaderboard> packageLeaderBoardList(Result<List<Book>> bookListResult) {
        List<Book> bookList = bookListResult.getData();
        List<Leaderboard> leaderBoardList = new ArrayList<>();
        for (Book book : bookList) {
            Leaderboard leaderboard = new Leaderboard();
            leaderboard.setBookId(book.getId());
            leaderboard.setBookAuthor(book.getAuthor());
            leaderboard.setBookImage(book.getImage());
            leaderboard.setBookName(book.getName());
            leaderboard.setCategory(categoryFeign.findByCategoryId(book.getCategory()).getData().getName());
            leaderboard.setReadNum(book.getReadNum());
            leaderboard.setSubscribeNum(book.getSubscribeNum());
            leaderBoardList.add(leaderboard);
        }
        return leaderBoardList;
    }

    private void updateWxImage() {
        Example example = new Example(Advertisement.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("position", "4");

        Advertisement advertisement = advertisementMapper.selectOneByExample(example);

        if (advertisement != null) {
            redisTemplate.opsForValue().set("wxImage", advertisement.getImage());
        }
    }

    private void updateCarousel() {
        Example example = generateExample("2");

        List<Advertisement> advertisementList = advertisementMapper.selectByExample(example);


        if (advertisementList != null || advertisementList.size() > 0) {
            redisTemplate.delete("carouselImage");
            redisTemplate.opsForList().leftPushAll("carouselImage", advertisementList);
        }

    }

    private void updateSmallImage() {

        Example example = generateExample("3");

        List<Advertisement> advertisementList = advertisementMapper.selectByExample(example);

        if (advertisementList != null || advertisementList.size() > 0) {
            redisTemplate.delete("smallImage");
            for (Advertisement advertisement : advertisementList) {
                redisTemplate.opsForList().leftPush("smallImage", advertisement);
            }
        }

    }

    private void updateBigImage() {

        Example example = generateExample("1");

        Advertisement advertisement = advertisementMapper.selectOneByExample(example);

        if (advertisement != null) {
            redisTemplate.opsForHash().put("bigImage", "url", advertisement.getUrl());
            redisTemplate.opsForHash().put("bigImage", "image", advertisement.getImage());
        }
    }

    private Example generateExample(String position) {
        Example example = new Example(Advertisement.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("position", position);
        criteria.andLessThan("startTime", new Date());
        criteria.andGreaterThan("endTime", new Date());
        return example;
    }


}
