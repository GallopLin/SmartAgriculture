package com.example;

import com.example.sensor.UdpDataReceiver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@EnableScheduling
public class Application implements CommandLineRunner {
    @Autowired
    private UdpDataReceiver udpDataReceiver;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }
    @Override
    public void run(String... args) throws Exception {
        // 启动UDP服务器并监听指定端口
        udpDataReceiver.startUdpServer(8088);

    }
}
