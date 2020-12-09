package com.zb.rabbitmq.service;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import com.zb.rabbitmq.constants.Constants;
import com.zb.rabbitmq.model.RabbitConsumerModel;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;


/**
 * rabbitmq消费者
 * Created by Zhangbing on 2018/12/1.
 */
@Component
public class ConsumerService {

    private volatile Integer count = 0;

    @RabbitListener(queues = {Constants.CONFIRM_FANOUT_QUEUE1, Constants.CONFIRM_FANOUT_QUEUE2})
    @RabbitHandler
    public void consumer(String message, Channel channel, @Headers Map map) {
        RabbitConsumerModel model = null;
        try {
            model = JSON.parseObject(JSON.toJSONString(map), RabbitConsumerModel.class);
            System.out.println("监听" + model.getAmqp_consumerQueue() + "队列的消费者接收到消息:" + message);
//            System.out.println(1 / 0);
            channel.basicAck(model.getAmqp_deliveryTag(), false);
        } catch (Exception e) {
            System.out.println("消息处理异常....");
            //如果处理消息异常，则调用basicNack或 basicReject 拒绝消息  让MQ重发
            try {
                //参数1 表示消息的id  参数2表示是否返回队列
                channel.basicReject(model.getAmqp_deliveryTag(), false);

                //在第一个参数DeliveryTag中如果输入3，则消息DeliveryTag小于等于3的，这个Channel的，都会被拒收
                //channel.basicNack(3, true, false);
            } catch (IOException e1) {

            }
        }
    }


    @RabbitListener(queues = Constants.LIMIT_DIRECT_QUEUE1)
    @RabbitHandler
    public void consumerReq1(String message, Channel channel, @Headers Map map) throws IOException {

        RabbitConsumerModel model = null;

        try {

            model = JSON.parseObject(JSON.toJSONString(map), RabbitConsumerModel.class);

            if (message.equals("下单成功:10")) {
                System.out.println(1 / 0);
            }

            System.out.println("监听" + model.getAmqp_consumerQueue() + "队列的消费者接收到消息:" + message);

            channel.basicAck(model.getAmqp_deliveryTag(), false);


        } catch (Exception e) {
            System.out.println("消息处理异常...." + message);
            //参数1 表示消息的id  参数2表示是否返回队列
//            channel.basicReject(model.getAmqp_deliveryTag(), false);
        }
    }


    @RabbitListener(queues = Constants.DEAD_MESSAGE_QUEUE)
    @RabbitHandler
    public void delayMessage(String message, Channel channel, @Headers Map map) throws IOException {

        RabbitConsumerModel model = null;

        try {

            model = JSON.parseObject(JSON.toJSONString(map), RabbitConsumerModel.class);
            System.out.println("消费者接收到消息:" + message + ",接收时间：" + System.currentTimeMillis());

            channel.basicAck(model.getAmqp_deliveryTag(), false);


        } catch (Exception e) {
            System.out.println("消息处理异常...." + message);
        }
    }

}
