package com.foxes.canal.listener;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.foxes.canal.config.ESrabbitMQConfig;
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

        String oldStatus = "0";
        String newStatus = "1";
        String str = "is_verify";
        //获取最新上架的商品 0->1
        if (oldStatus.equals(oldData.get(str)) && newStatus.equals(newData.get(str))){
            //将小说的id发送到mq
            rabbitTemplate.convertAndSend(RabbitMQConfig.BOOK_UP_EXCHANGE,"",newData.get("id"));
        }
        //是否下架
        if (oldStatus.equals(newData.get(str)) && newStatus.equals(oldData.get(str))){
            //是下架,ES执行删除
            rabbitTemplate.convertAndSend(ESrabbitMQConfig.BOOK_ESUP_EXCHANGE,"",newData.get("id")+",down");
        }else {
            //不是下架,ES更新
            rabbitTemplate.convertAndSend(ESrabbitMQConfig.BOOK_ESUP_EXCHANGE,"",newData.get("id")+",up");
        }


    }
}
