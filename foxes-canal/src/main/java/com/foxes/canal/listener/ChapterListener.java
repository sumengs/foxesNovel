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
 * @date: 2020/6/30 16:14
 * @author: sumeng
 */
@CanalEventListener
public class ChapterListener {


    @Autowired
    private RabbitTemplate rabbitTemplate;


    @ListenPoint(schema = "foxes-novel", table = "tb_book_chapter")
    public void goodsUp(CanalEntry.EventType eventType, CanalEntry.RowData rowData) {

        //获取改变之后的数据并这部分数据转换为map
        Map<String, String> newData = new HashMap<>();

        rowData.getAfterColumnsList().forEach((c) -> newData.put(c.getName(), c.getValue()));

        if ("1".equals(newData.get("is_delete")) || "0".equals(newData.get("is_verify"))) {
            rabbitTemplate.convertAndSend(RabbitMQConfig.CHAPTER_DELETE_EXCHANGE, "", newData.get("id"));
        } else {
            rabbitTemplate.convertAndSend(RabbitMQConfig.CHAPTER_EXCHANGE, "", newData.get("id"));
        }

    }

}
