package com.consumer.service;


import com.consumer.entity.MessageEntity;
import com.consumer.repo.MessageRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class KafkaConsumerService {
    @Autowired
    private final MessageRepository messageRepository;
    @Autowired
    private final ObjectMapper objectMapper;

    @Autowired
    public KafkaConsumerService(MessageRepository messageRepository, ObjectMapper objectMapper) {
        this.messageRepository = messageRepository;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "topicTest", groupId = "my_consumer_group")
    public void consumeMessage(String jsonString) {
        System.out.println("Received message: " + jsonString);

        try {
            MessageEntity messageEntity = objectMapper.readValue(jsonString, MessageEntity.class);

            messageRepository.save(messageEntity);
        } catch (IOException e) {
            System.err.println("Error deserializing JSON: " + e.getMessage());
        }
    }
}
