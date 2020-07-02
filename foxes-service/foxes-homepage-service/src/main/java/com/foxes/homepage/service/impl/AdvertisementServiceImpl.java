package com.foxes.homepage.service.impl;

import com.foxes.book.feign.BookFeign;
import com.foxes.book.pojo.Book;
import com.foxes.homepage.dao.AdvertisementMapper;
import com.foxes.chapter.pojo.Advertisement;
import com.foxes.homepage.service.AdvertisementService;
import com.sumeng.peekshopping.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

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

    @Override
    public List<Advertisement> carousel() {

        Example example = new Example(Advertisement.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("position", "2");

        return advertisementMapper.selectByExample(example);
    }

    @Override
    public Advertisement bigImage() {
        Example example = new Example(Advertisement.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("position", "1");

        return advertisementMapper.selectOneByExample(example);
    }

    @Override
    public List<Advertisement> smallImage() {
        Example example = new Example(Advertisement.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("position", "3");

        return advertisementMapper.selectByExample(example);
    }

    @Override
    public Result<List<Book>> subscribeList() {
        return bookFeign.findTopTenSubscribe();
    }

    @Override
    public Result<List<Book>> readList() {
        return bookFeign.findTopTenRead();
    }
}
