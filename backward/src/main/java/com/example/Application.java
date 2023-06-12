package com.example;

import com.example.sensor.SensorDataSender;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        try {
            // 创建传感器数据发送器，并启动发送线程
            SensorDataSender dataSender = new SensorDataSender("localhost", 8888);
            Thread senderThread = new Thread(dataSender);
            senderThread.start();

            // 主线程等待一段时间后停止发送线程
            Thread.sleep(600000); // 运行60秒
            dataSender.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @Bean
//    public ServerEndpointExporter serverEndpointExporter() {
//        return new ServerEndpointExporter();
//    }

//    @Bean
//    public SensorDataHandler sensorDataHandler() {
//        return new SensorDataHandler();
//    }
}
