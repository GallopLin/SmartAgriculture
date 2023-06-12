package com.example.sensor;

import com.alibaba.fastjson.JSON;
import com.example.dao.bo.Information;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.NumberFormat;
import java.time.LocalDateTime;

public class SensorDataSender implements Runnable {
    private DatagramSocket socket;
    private InetAddress serverAddress;
    private int serverPort;
    private TemperatureSensor dataGenerator;
    private volatile boolean running;

    public SensorDataSender(String serverIpAddress, int serverPort) throws Exception {
        this.serverAddress = InetAddress.getByName(serverIpAddress);
        this.serverPort = serverPort;
        this.socket = new DatagramSocket();
        this.dataGenerator = new TemperatureSensor();
        this.running = true;
    }

    @Override
    public void run() {
        while (running) {
            try {
                // 生成传感器数据
                double soilMoisture = dataGenerator.generateTemperature(50.0, 60.0);
                double soilTemperature = dataGenerator.generateTemperature(20.0, 23.0);
                double ph = dataGenerator.generateTemperature(7.0, 7.5);
                double airTemperature = dataGenerator.generateTemperature(20.0, 23.0);
                double airHumidity = dataGenerator.generateTemperature(50.0, 60.0);
                double lightIntensity = dataGenerator.generateTemperature(3000.0, 3100.0);
                LocalDateTime createTime = LocalDateTime.now();
                Information information = new Information(soilMoisture,soilTemperature,ph,airTemperature,airHumidity,lightIntensity,createTime);
                ObjectMapper mapper = new ObjectMapper();
                // 构造数据包
                String sensorData = mapper.writeValueAsString(information);
                byte[] sendData = sensorData.getBytes();
                DatagramPacket packet = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);

                // 发送数据包
                socket.send(packet);

                // 休眠一段时间，模拟实时数据产生频率
                Thread.sleep(10000); // 每秒发送一次数据
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        socket.close();
    }

    public void stop() {
        running = false;
    }
}
