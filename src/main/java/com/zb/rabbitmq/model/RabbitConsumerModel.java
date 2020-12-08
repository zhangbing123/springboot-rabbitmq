package com.zb.rabbitmq.model;

/**
 * @description:
 * @author: zhangbing
 * @create: 2020-12-08 11:05
 **/
public class RabbitConsumerModel {

    private String amqp_receivedDeliveryMode;
    private String amqp_contentEncoding;
    private String spring_listener_return_correlation;
    private String amqp_receivedExchange;
    private String spring_returned_message_correlation;
    private long amqp_deliveryTag;
    private String amqp_consumerQueue;
    private boolean amqp_redelivered;
    private String id;
    private String amqp_consumerTag;
    private String contentType;
    private String timestamp;

    public String getAmqp_receivedDeliveryMode() {
        return amqp_receivedDeliveryMode;
    }

    public void setAmqp_receivedDeliveryMode(String amqp_receivedDeliveryMode) {
        this.amqp_receivedDeliveryMode = amqp_receivedDeliveryMode;
    }

    public String getAmqp_contentEncoding() {
        return amqp_contentEncoding;
    }

    public void setAmqp_contentEncoding(String amqp_contentEncoding) {
        this.amqp_contentEncoding = amqp_contentEncoding;
    }

    public String getSpring_listener_return_correlation() {
        return spring_listener_return_correlation;
    }

    public void setSpring_listener_return_correlation(String spring_listener_return_correlation) {
        this.spring_listener_return_correlation = spring_listener_return_correlation;
    }

    public String getAmqp_receivedExchange() {
        return amqp_receivedExchange;
    }

    public void setAmqp_receivedExchange(String amqp_receivedExchange) {
        this.amqp_receivedExchange = amqp_receivedExchange;
    }

    public String getSpring_returned_message_correlation() {
        return spring_returned_message_correlation;
    }

    public void setSpring_returned_message_correlation(String spring_returned_message_correlation) {
        this.spring_returned_message_correlation = spring_returned_message_correlation;
    }

    public long getAmqp_deliveryTag() {
        return amqp_deliveryTag;
    }

    public void setAmqp_deliveryTag(long amqp_deliveryTag) {
        this.amqp_deliveryTag = amqp_deliveryTag;
    }

    public boolean isAmqp_redelivered() {
        return amqp_redelivered;
    }

    public void setAmqp_redelivered(boolean amqp_redelivered) {
        this.amqp_redelivered = amqp_redelivered;
    }

    public String getAmqp_consumerQueue() {
        return amqp_consumerQueue;
    }

    public void setAmqp_consumerQueue(String amqp_consumerQueue) {
        this.amqp_consumerQueue = amqp_consumerQueue;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAmqp_consumerTag() {
        return amqp_consumerTag;
    }

    public void setAmqp_consumerTag(String amqp_consumerTag) {
        this.amqp_consumerTag = amqp_consumerTag;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
