//package com.zb.rabbitmq;
//
//import com.zb.rabbitmq.service.ConsumerService;
//import com.zb.rabbitmq.service.ProducerService;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.io.IOException;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class RabbitmqApplicationTests {
//
//    @Autowired
//    ProducerService producerService;//消息生产者
//
//    @Autowired
//    ConsumerService consumerService;//消息消费者
//
//    /**
//     * 生成发布消息
//     */
//    @Test
//    public void produce() throws IOException, InterruptedException {
//        String message = "hello,rabbitmq";
//        producerService.publisher(message);
//    }
//
//    /**
//     * 消费消息
//     * @throws IOException
//     */
//    @Test
//    public void consumer() throws IOException {
//        consumerService.custome();
//    }
//
//    @Test
//    public void contextLoads() {
//    }
//
//}
