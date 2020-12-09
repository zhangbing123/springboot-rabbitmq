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
public class MqProduceController {

    @Autowired
    private ProducerService producerService;

    @RequestMapping("/send1")
    public String sendMessage(String message, String routingKey) {
        producerService.publisher(message, routingKey);
        return "消息发送成功";
    }

    @RequestMapping("/limiting")
    public String limiting(String message, String routingKey) {
        producerService.limiting(message, routingKey);
        return "消息发送成功";
    }


    @RequestMapping("/ttlForSingleMsg")
    public String ttlForSingleMsg(String message, String routingKey) {
        producerService.ttlForSingleMsg(message, routingKey);
        return "消息发送成功";
    }

    @RequestMapping("/ttlForQueue")
    public String ttlForQueue(String message, String routingKey) {
        producerService.ttlForQueue(message, routingKey);
        return "消息发送成功";
    }

    @RequestMapping("/sendDelayMsg")
    public String sendDelayMsg(String message, String routingKey) {
        producerService.sendDelayMsg(message, routingKey);
        return "消息发送成功";
    }

}
