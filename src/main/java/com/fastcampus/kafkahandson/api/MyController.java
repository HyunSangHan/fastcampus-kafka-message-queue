package com.fastcampus.kafkahandson.api;

import com.fastcampus.kafkahandson.model.MyMessage;
import com.fastcampus.kafkahandson.producer.MyProducer;
import com.fastcampus.kafkahandson.producer.MySecondProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class MyController {

    private final MyProducer myProducer;
    private final MySecondProducer mySecondProducer;

    @RequestMapping("/hello")
    String hello() {
        return "Hello World";
    }

    @PostMapping("/message")
    void message(
            @RequestBody MyMessage message
    ) {
        myProducer.sendMessage(message);
    }

    @PostMapping("/second-message/{key}")
    void message(
        @PathVariable String key,
        @RequestBody String message
    ) {
        mySecondProducer.sendMessageWithKey(key, message);
    }
}
