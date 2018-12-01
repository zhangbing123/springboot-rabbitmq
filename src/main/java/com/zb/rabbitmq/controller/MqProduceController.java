package com.zb.rabbitmq.controller;

import com.zb.rabbitmq.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by Zhangbing on 2018/12/1.
 */
@RestController
public class MqProduceController{

    @Autowired
    private ProducerService producerService;

    @RequestMapping("/send1")
    public String sendMessage(String message) {
       producerService.publisher(message);
       return "消息发送成功";
    }

}
