package com.zb.rabbitmq.config;

import com.zb.rabbitmq.constants.Constants;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;


/**
 * @description:
 * @author: zhangbing
 * @create: 2020-12-09 13:40
 **/
@Configuration
public class TtlConfig {

    @Bean
    public Queue ttlQueue() {
        //设置消息过期时间10s
        HashMap<String, Object> map = new HashMap<>();
        map.put("x-message-ttl", 20000);//10s
        return new Queue(Constants.TTL_TEST_QUEUE2, false, false, false, map);
    }
}
