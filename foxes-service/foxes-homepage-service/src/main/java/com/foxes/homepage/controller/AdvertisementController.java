package com.foxes.homepage.controller;

import com.foxes.read.pojo.Book;
import com.foxes.chapter.pojo.Advertisement;
import com.foxes.chapter.pojo.Leaderboard;
import com.foxes.homepage.service.AdvertisementService;
import com.sumeng.peekshopping.constant.StatusCode;
import com.sumeng.peekshopping.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @date: 2020/7/2 13:48
 * @author: sumeng
 */
@Controller
public class AdvertisementController {


    @Autowired
    private AdvertisementService advertisementService;


    @RequestMapping("/carousel")
    @ResponseBody
    public Result<List<Advertisement>> carousel() {
        List<Advertisement> carousel = advertisementService.carousel();
        return new Result<>(true, StatusCode.OK, "轮播图数据返回成功", carousel);
    }


    @RequestMapping("/small")
    @ResponseBody
    public Result<List<Advertisement>> smallImage() {

        List<Advertisement> smallImage = advertisementService.smallImage();
        return new Result<>(true, StatusCode.OK, "小横幅返回成功", smallImage);
    }


    @RequestMapping("/big")
    @ResponseBody
    public Result<Advertisement> bigImage() {
        Advertisement bigImage = advertisementService.bigImage();
        return new Result<>(true, StatusCode.OK, "大横幅返回成功", bigImage);
    }

    @RequestMapping("/weixin")
    @ResponseBody
    public Result<Advertisement> weixinImage() {
        Advertisement weixinImage = advertisementService.weixinImage();
        return new Result<>(true, StatusCode.OK, "微信二维码返回成功", weixinImage);
    }


    @ResponseBody
    @RequestMapping("/subscribe")
    public Result<List<Book>> subscribeList() {
        List<Leaderboard> books = advertisementService.subscribeList();
        return new Result<>(true, StatusCode.OK, "订阅榜前10返回成功", books);
    }


    @ResponseBody
    @RequestMapping("/read")
    public Result<List<Book>> readList() {
        List<Leaderboard> books = advertisementService.readList();
        return new Result<>(true, StatusCode.OK, "热度榜前10返回成功", books);
    }

}
