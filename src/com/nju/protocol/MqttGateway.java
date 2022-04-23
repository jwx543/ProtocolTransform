package com.nju.protocol;

import com.nju.protocol.data.DetectData;
import com.nju.protocol.utils.DataHandle;
import com.nju.protocol.utils.SendMessage;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class MqttGateway {
    public static void main(String[] args) {

        List<DetectData> data = new DataHandle().readFile();
        int len = data.size();
        int index = 0;

        String topic = "";
        String content = "{\"serialNumber\": \"SN-001\", \"sensorType\": \"Thermometer\", \"sensorModel\": \"T1000\", \"temp\": 2.2, \"hum\": 18}";
        int qos = 0;
        String broker = "tcp://103.47.82.178:1234";
        String userName = "nju";
        String password = "Nju+2022";
        String clientId = "javaClient";
        // 内存存储
        MemoryPersistence persistence = new MemoryPersistence();

        try {
            // 创建客户端
            MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
            // 创建链接参数
            MqttConnectOptions connOpts = new MqttConnectOptions();
            // 在重新启动和重新连接时记住状态
            connOpts.setCleanSession(false);
            // 设置连接的用户名
            connOpts.setUserName(userName);
            connOpts.setPassword(password.toCharArray());
            // 建立连接
            sampleClient.connect(connOpts);
            while(true){
                topic = "/sensor/thermometerData";
                content = "{\"serialNumber\": \"NJ-temp\", \"sensorType\": \"Thermometer\", \"sensorModel\": \"T1000\", \"temp\": ";
                content += data.get(index).getTemperature().toString();
                content += ", \"hum\": ";
                content += data.get(index).getHumidity().toString();
                content += ", \"humrat\": ";
                content += data.get(index).getHumidityRatio().toString();
                content += "}";
                System.out.println(content);
                new SendMessage().send(topic, qos, content, sampleClient);

                topic = "/sensor/lightData";
                content = "{\"serialNumber\": \"NJ-light\", \"sensorType\": \"Light\", \"sensorModel\": \"L1000\", \"light\": ";
                content += data.get(index).getLight().toString();
                content += "}";
                System.out.println(content);
                new SendMessage().send(topic, qos, content, sampleClient);

                topic = "/sensor/airData";
                content = "{\"serialNumber\": \"NJ-air\", \"sensorType\": \"Air\", \"sensorModel\": \"A1000\", \"co2\": ";
                content += data.get(index).getCO2().toString();
                content += "}";
                System.out.println(content);
                new SendMessage().send(topic, qos, content, sampleClient);

                index = (index + 1) % len;
                TimeUnit.SECONDS.sleep(5);
            }

//            // 断开连接
//            sampleClient.disconnect();
//            // 关闭客户端
//            sampleClient.close();
        } catch (MqttException me) {
            System.out.println("reason " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("excep " + me);
            me.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
