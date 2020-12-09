package com.zb.rabbitmq.config;

import com.zb.rabbitmq.constants.Constants;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;

/**
 * @description:
 * @author: zhangbing
 * @create: 2020-12-09 15:11
 **/
public class DelayConfig {

    @Bean
    public Queue delayQueue() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("x-dead-letter-exchange", Constants.DLX_EXCHANGE);
        map.put("x-dead-letter-routing-key", "dead_routing");
        return new Queue(Constants.DELAY_QUEUE, true, false, false, map);
    }

    @Bean
    public DirectExchange delayExchange() {
        return new DirectExchange(Constants.DELAY_EXCHANGE);
    }

    @Bean
    public Binding delayBinding() {
        return BindingBuilder.bind(delayQueue()).to(delayExchange()).with("delay_routing");
    }


    @Bean
    public Queue deadQueue() {
        return new Queue(Constants.DEAD_MESSAGE_QUEUE);
    }

    @Bean
    public DirectExchange deadExchange() {
        return new DirectExchange(Constants.DLX_EXCHANGE);
    }

    @Bean
    public Binding deadBinding() {
        return BindingBuilder.bind(deadQueue()).to(deadExchange()).with("dead_routing");
    }

}
