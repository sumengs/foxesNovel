package com.foxes.homepage.service;

import com.foxes.book.pojo.Book;
import com.foxes.chapter.pojo.Advertisement;
import com.sumeng.peekshopping.entity.Result;

import java.util.List;

/**
 * @date: 2020/7/2 13:51
 * @author: sumeng
 */
public interface AdvertisementService {

    /**
     * 轮播图
     *
     * @return
     */
    List<Advertisement> carousel();

    /**
     * 大横幅
     *
     * @return
     */
    Advertisement bigImage();


    /**
     * 小横幅
     *
     * @return
     */
    List<Advertisement> smallImage();


    /**
     * 订阅榜
     *
     * @return
     */
    Result<List<Book>> subscribeList();


    /**
     * 阅读榜单
     *
     * @return
     */
    Result<List<Book>> readList();
}
