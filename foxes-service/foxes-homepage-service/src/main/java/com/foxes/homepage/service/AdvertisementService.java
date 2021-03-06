package com.foxes.homepage.service;

import com.foxes.chapter.pojo.Advertisement;
import com.foxes.chapter.pojo.Leaderboard;

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
     * 微信二维码
     *
     * @return
     */
    Advertisement weixinImage();


    /**
     * 订阅榜
     *
     * @return
     */
    List<Leaderboard> subscribeList();


    /**
     * 阅读榜单
     *
     * @return
     */
    List<Leaderboard> readList();


    /**
     * 更新Redis
     */
    void updateRedis();
}
