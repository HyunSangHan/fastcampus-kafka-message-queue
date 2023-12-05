package com.fastcampus.kafkahandson.producer;

import com.fastcampus.kafkahandson.model.MyMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MyProducer {

    private final KafkaTemplate<String, MyMessage> kafkaTemplate;

    public void sendMessage(MyMessage myMessage) {
        kafkaTemplate.send("my-json-topic", String.valueOf(myMessage.getAge()), myMessage);
    }
}
