package com.fastcampus.kafkahandson.producer;

import com.fastcampus.kafkahandson.model.MyMessage;
import com.fastcampus.kafkahandson.model.Topic;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MyProducer {

    private final KafkaTemplate<String, MyMessage> kafkaTemplate;

    public void sendMessage(MyMessage myMessage) {
        kafkaTemplate.send(Topic.MY_JSON_TOPIC, String.valueOf(myMessage.getAge()), myMessage);
    }
}
