package com.foxes.homepage.service.impl;

import com.foxes.homepage.dao.AdvertisementMapper;
import com.foxes.chapter.pojo.Advertisement;
import com.foxes.homepage.service.AdvertisementService;
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


    @Override
    public List<Advertisement> carousel() {

        Example example = new Example(Advertisement.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("position", "2");

        return advertisementMapper.selectByExample(example);
    }
}
