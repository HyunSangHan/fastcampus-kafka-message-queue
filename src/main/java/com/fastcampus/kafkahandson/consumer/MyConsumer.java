package com.fastcampus.kafkahandson.consumer;

import com.fastcampus.kafkahandson.model.MyMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.fastcampus.kafkahandson.model.Topic.MY_JSON_TOPIC;

@Component
public class MyConsumer {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Map<String, Integer> idHistoryMap = new ConcurrentHashMap<>(); // id에 대해 Exactly Once를 보장하기 위함

    @KafkaListener(
        topics = { MY_JSON_TOPIC },
        groupId = "test-consumer-group",
        concurrency = "1"
    )
    public void listen(ConsumerRecord<String, String> message, Acknowledgment acknowledgment) throws JsonProcessingException {
        MyMessage myMessage = objectMapper.readValue(message.value(), MyMessage.class);
        this.printPayloadIfFirstMessage(myMessage);
        acknowledgment.acknowledge();
    }

    private void printPayloadIfFirstMessage(MyMessage myMessage) {
        if (idHistoryMap.putIfAbsent(String.valueOf(myMessage.getId()), 1) == null) {
            System.out.println("[Main Consumer(" + Thread.currentThread().getId() + ")] Message arrived! - " + myMessage); // Exactly Once 실행되어야 하는 로직으로 가정
        } else {
            System.out.println("[Main Consumer(" + Thread.currentThread().getId() + ")] Duplicate! (" + myMessage.getId() + ")");
        }
    }
}
