package com.zb.rabbitmq.config;

import com.zb.rabbitmq.constants.Constants;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: zhangbing
 * @create: 2020-12-08 16:55
 **/
@Configuration
public class FanoutPubSubConfig {


    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(Constants.CONFIRM_FANOUT_EXCHANGE, true, false);
    }

    @Bean
    public Queue confirmQueue1() {
        return new Queue(Constants.CONFIRM_FANOUT_QUEUE1, true);
    }

    @Bean
    public Queue confirmQueue2() {
        return new Queue(Constants.CONFIRM_FANOUT_QUEUE2, true);
    }

    @Bean
    public Binding confirmBinding1() {
        return BindingBuilder.bind(confirmQueue1()).to(fanoutExchange());
    }

    @Bean
    public Binding confirmBinding2() {
        return BindingBuilder.bind(confirmQueue2()).to(fanoutExchange());
    }

}
