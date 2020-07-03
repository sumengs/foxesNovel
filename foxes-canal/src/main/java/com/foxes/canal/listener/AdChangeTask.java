package com.foxes.canal.listener;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.foxes.canal.config.AdRabbitMQConfig;
import com.foxes.canal.config.RabbitMQConfig;
import com.xpand.starter.canal.annotation.CanalEventListener;
import com.xpand.starter.canal.annotation.ListenPoint;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @date: 2020/7/3 9:52
 * @author: sumeng
 */
@Component
public class AdChangeTask {
    @Autowired
    private RabbitTemplate rabbitTemplate;


    @Scheduled(cron = "0/5 * * * * ?")
    public void adChangeTask() {
        Date date = new Date();
        System.out.println(date);
        rabbitTemplate.convertAndSend(AdRabbitMQConfig.AD_EXCHANGE, "", date);

    }
}
