package com.foxes.homepage.controller;

import com.foxes.chapter.pojo.Advertisement;
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
    public Result<Advertisement> carousel() {

        List<Advertisement> carousel = advertisementService.carousel();
        return new Result<>(true, StatusCode.OK, "轮播图数据返回成功", carousel);
    }

}
