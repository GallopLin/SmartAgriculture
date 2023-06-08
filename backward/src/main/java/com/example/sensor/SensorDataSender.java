package com.example.sensor;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

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
                double temperature = dataGenerator.generateTemperature();

                // 构造数据包
                String sensorData = String.valueOf(temperature);
                byte[] sendData = sensorData.getBytes();
                DatagramPacket packet = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);

                // 发送数据包
                socket.send(packet);

                // 休眠一段时间，模拟实时数据产生频率
                Thread.sleep(1000); // 每秒发送一次数据
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
