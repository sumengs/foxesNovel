package com.foxes.homepage.listener;

import com.foxes.homepage.config.AdRabbitMQConfig;
import com.foxes.homepage.service.AdvertisementService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @date: 2020/7/3 9:57
 * @author: sumeng
 */
@Component
public class AdExchangeListener {


    @Autowired
    private AdvertisementService advertisementService;


    @RabbitListener(queues = AdRabbitMQConfig.AD_QUEUE)
    public void updateMessage(Date date) {
        System.out.println("获取到广告更新的信息，更新广告redis缓存,当前时间为:" + date);
        advertisementService.updateRedis();
    }

}
