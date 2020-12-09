package com.zb.rabbitmq.constants;

/**
 * 常量池
 */
public class Constants {

    public final static String HELLOWORLD_QUEUE = "helloworld_queue";
    public final static String WORK_QUEUE = "work_queue";
    public final static String STOCK_QUEUE = "stoc_queue";//库存服务监听的队列
    public final static String USER_QUEUE = "user_queue";//用户服务监听队列
    public final static String CONFIRM_FANOUT_QUEUE1 = "confirm_Fanout_queue1";
    public final static String CONFIRM_FANOUT_QUEUE2 = "confirm_Fanout_queue2";
    public final static String LIMIT_DIRECT_QUEUE1 = "limit_direct_queue1";
    public final static String LIMIT_DIRECT_QUEUE2 = "limit_direct_queue2";
    public final static String TTL_TEST_QUEUE = "ttl_test_queue";//设置过期时间的队列
    public final static String TTL_TEST_QUEUE2 = "ttl_test_queue2";//针对每条消息设置过期时间
    public final static String DEAD_MESSAGE_QUEUE = "dead_message_queue";//死信队列
    public final static String DELAY_QUEUE = "delay_queue";//延迟队列


    public final static String PUBSUB_EXCHANGE = "pubSub_exchange";
    public final static String ROUTING_EXCHANGE = "routing_exchange";
    public final static String TOPIC_EXCHANGE = "topic_exchange";
    public static final String CONFIRM_FANOUT_EXCHANGE = "confirm_fanout_exchange";//可靠性投递测试的交换机
    public static final String LIMIT_DIRECT_EXCHANGE = "limit_direct_exchange";//限流
    public static final String DELAY_EXCHANGE = "delay_exchange";//延时交换机
    public static final String DLX_EXCHANGE = "dlx_exchange";//死信交换机
}
