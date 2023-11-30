package com.fastcampus.kafkahandson.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @RequestMapping("/hello")
    String hello() {
        return "Hello World";
    }
}
