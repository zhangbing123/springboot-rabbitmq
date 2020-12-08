package com.zb.rabbitmq.controller;

import com.zb.rabbitmq.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 消息生产者
 * Created by Zhangbing on 2018/12/1.
 */
@RestController
public class MqProduceController{

    @Autowired
    private ProducerService producerService;

    @RequestMapping("/send1")
    public String sendMessage(String message,String routingKey) {
       producerService.publisher(message,routingKey);
       return "消息发送成功";
    }

    @RequestMapping("/limiting")
    public String limiting(String message,String routingKey) {
        producerService.limiting(message,routingKey);
        return "消息发送成功";
    }

}
