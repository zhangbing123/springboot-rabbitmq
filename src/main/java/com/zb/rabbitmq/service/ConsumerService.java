package com.zb.rabbitmq.service;

import org.springframework.stereotype.Component;



/**
 * rabbitmq消费者
 * Created by Zhangbing on 2018/12/1.
 */
@Component
public class ConsumerService {


    public void custome(String msg){
        System.out.println("test1 receiver message:"+msg);
    }

}
