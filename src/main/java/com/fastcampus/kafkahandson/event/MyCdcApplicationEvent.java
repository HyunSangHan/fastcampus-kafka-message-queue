package com.fastcampus.kafkahandson.event;

import com.fastcampus.kafkahandson.model.MyModel;
import com.fastcampus.kafkahandson.model.OperationType;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class MyCdcApplicationEvent extends ApplicationEvent {

    private final Integer id;
    private final MyModel myModel;
    private final OperationType operationType;

    public MyCdcApplicationEvent(Object source, Integer id, MyModel myModel, OperationType operationType) {
        super(source);
        this.id = id;
        this.myModel = myModel;
        this.operationType = operationType;
    }
}
