package com.fastcampus.kafkahandson.event;

import com.fastcampus.kafkahandson.model.MyModelConverter;
import com.fastcampus.kafkahandson.producer.MyCdcProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@RequiredArgsConstructor
@Component
public class MyCdcApplicationEventListener {

    private final MyCdcProducer myCdcProducer;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Async
    public void transactionalEventListenerAfterCommit(MyCdcApplicationEvent event) throws JsonProcessingException {
        myCdcProducer.sendMessage(
            MyModelConverter.toMessage(event.getId(), event.getMyModel(), event.getOperationType())
        );
    }
}
