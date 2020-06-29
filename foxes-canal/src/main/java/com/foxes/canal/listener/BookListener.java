package com.foxes.canal.listener;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.foxes.canal.config.RabbitMQConfig;
import com.xpand.starter.canal.annotation.CanalEventListener;
import com.xpand.starter.canal.annotation.ListenPoint;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * @date: 2020/6/28 19:10
 * @author: lenaminz
 */
@CanalEventListener
public class BookListener {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @ListenPoint(schema = "foxes-novel",table = "tb_book")
    public void goodsUp(CanalEntry.EventType eventType, CanalEntry.RowData rowData){
        //获取改变之前的数据并将这部分数据转换为map
        Map<String,String> oldData=new HashMap<>();
        rowData.getBeforeColumnsList().forEach((c)->oldData.put(c.getName(),c.getValue()));

        //获取改变之后的数据并这部分数据转换为map
        Map<String,String> newData = new HashMap<>();
        rowData.getAfterColumnsList().forEach((c)->newData.put(c.getName(),c.getValue()));

        //获取最新上架的商品 0->1
        if ("0".equals(oldData.get("is_verify")) && "1".equals(newData.get("is_verify"))){
            //将小说的id发送到mq
            rabbitTemplate.convertAndSend(RabbitMQConfig.BOOK_UP_EXCHANGE,"",newData.get("id"));
        }

    }
}
