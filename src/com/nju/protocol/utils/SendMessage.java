package com.nju.protocol.utils;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.concurrent.TimeUnit;

public class SendMessage {

    public void send(String topic, int qos, String content, MqttClient sampleClient) throws MqttException, InterruptedException {

        // 创建消息
        MqttMessage message = new MqttMessage(content.getBytes());
        // 设置消息的服务质量
        message.setQos(qos);
        // 发布消息
        sampleClient.publish(topic, message);
    }
}
