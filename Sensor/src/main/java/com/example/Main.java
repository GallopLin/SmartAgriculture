package com.example;

import com.example.sensor.Sensor;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Main {
    public static void main(String[] args) throws SocketException {
        Sensor dataGenerator = new Sensor();
        DatagramSocket socket = new DatagramSocket();
        while (true) {
            try {
                // 构造数据包
                String sensorData = dataGenerator.generateData();
                byte[] sendData = sensorData.getBytes();
                DatagramPacket packet = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("localhost"), 8088);
                // 发送数据包
                socket.send(packet);
                // 休眠一段时间，模拟实时数据产生频率
                Thread.sleep(15000); // 每15秒发送一次数据
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}