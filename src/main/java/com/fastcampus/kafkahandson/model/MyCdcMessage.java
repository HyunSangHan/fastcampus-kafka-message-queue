package com.fastcampus.kafkahandson.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyCdcMessage {
    private int id;
    private Payload payload;
    private OperationType operationType;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Payload {
        private int userId;
        private int userAge;
        private String userName;
        private String content;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }
}
