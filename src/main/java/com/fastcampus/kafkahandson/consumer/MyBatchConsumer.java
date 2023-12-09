package com.fastcampus.kafkahandson.consumer;

import com.fastcampus.kafkahandson.model.MyMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.fastcampus.kafkahandson.model.Topic.MY_JSON_TOPIC;

@Component
public class MyBatchConsumer {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(
        topics = { MY_JSON_TOPIC },
        groupId = "batch-test-consumer-group", // MyConsumer의 groupId와 반드시 달라야 함!
        containerFactory = "batchKafkaListenerContainerFactory",
        concurrency = "1"
    )
    public void listen(List<ConsumerRecord<String, String>> messages) {
        System.out.println("[Batch Consumer] Batch message arrived! - count " + messages.size());
        messages.forEach(message -> {
            MyMessage myMessage;
            try {
                myMessage = objectMapper.readValue(message.value(), MyMessage.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            System.out.println("ㄴ [Batch Consumer] Value - " + myMessage + " / Offset - " + message.offset() + " / Partition - " + message.partition());
        });
    }
}
