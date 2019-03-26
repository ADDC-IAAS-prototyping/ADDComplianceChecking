package com.example.demo.service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

import com.example.demo.cepEngine.handler.CEPEventHandler;
import com.example.demo.cepEngine.model.HttpRequestEvent;
import com.example.demo.cepEngine.model.VirtualMachine;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqService {

    @Autowired
    private CEPEventHandler eventHandler;


   // private final static String QUEUE_NAME = "virtualMachineEvents";
    private final static String HOST = "localhost";


    public void start(String queueName, DeliverCallback deliverCallback) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(queueName, false, false, false, null);

        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
        });
    }

}
