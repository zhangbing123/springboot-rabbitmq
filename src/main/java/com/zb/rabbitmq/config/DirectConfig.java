package com.zb.rabbitmq.config;

import com.zb.rabbitmq.constants.Constants;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @description:
 * @author: zhangbing
 * @create: 2020-12-08 16:59
 **/
@Configuration
public class DirectConfig {


    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(Constants.LIMIT_DIRECT_EXCHANGE, true, false);
    }

    @Bean
    public Queue limitQueue1() {
        return new Queue(Constants.LIMIT_DIRECT_QUEUE1, true);
    }

    @Bean
    public Queue limitQueue2() {
        return new Queue(Constants.LIMIT_DIRECT_QUEUE2, true);
    }

    @Bean
    public Binding limitBinding1() {
        return BindingBuilder.bind(limitQueue1()).to(directExchange()).with("commitOrder"); //提交订单请求
    }

    @Bean
    public Binding limitBinding2() {
        return BindingBuilder.bind(limitQueue2()).to(directExchange()).with("payReq");//支付请求
    }

}
