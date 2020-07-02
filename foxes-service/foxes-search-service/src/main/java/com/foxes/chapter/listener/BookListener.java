package com.foxes.chapter.listener;


import com.foxes.chapter.service.ESFoxManageService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "bookinfo_esup_queue")
public class BookListener {

    @Autowired
    private ESFoxManageService esFoxManageService;

    @RabbitHandler
    public void receiveMessage(String message){
        System.out.println("收到的消息:" + message);

        String[] split = message.split(",");
        String id = split[0];
        String s = split[1];
        System.out.println(id+":"+s);

        if (s.equals("down")) {
            esFoxManageService.deleteById(id);
        }
        if (s.equals("up")) {
            esFoxManageService.importById(id);
        }
    }
}
