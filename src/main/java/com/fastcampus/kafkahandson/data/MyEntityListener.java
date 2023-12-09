package com.fastcampus.kafkahandson.data;

import com.fastcampus.kafkahandson.model.MyModel;
import com.fastcampus.kafkahandson.model.MyModelConverter;
import com.fastcampus.kafkahandson.model.OperationType;
import com.fastcampus.kafkahandson.producer.MyCdcProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class MyEntityListener {

    @Lazy
    @Autowired
    private MyCdcProducer myCdcProducer;

    @PostPersist
    public void handleCreate(MyEntity myEntity) {
        System.out.println("handleCreate");
        MyModel myModel = MyModelConverter.toModel(myEntity);
        try {
            myCdcProducer.sendMessage(
                MyModelConverter.toMessage(
                    myModel.getId(),
                    myModel,
                    OperationType.CREATE
                )
            );
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @PostUpdate
    public void handleUpdate(MyEntity myEntity) {
        System.out.println("handleUpdate");
        MyModel myModel = MyModelConverter.toModel(myEntity);
        try {
            myCdcProducer.sendMessage(
                MyModelConverter.toMessage(
                    myModel.getId(),
                    myModel,
                    OperationType.UPDATE
                )
            );
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @PostRemove
    public void handleDelete(MyEntity myEntity) {
        System.out.println("handleDelete");
        MyModel myModel = MyModelConverter.toModel(myEntity);
        try {
            myCdcProducer.sendMessage(
                MyModelConverter.toMessage(
                    myModel.getId(),
                    null,
                    OperationType.DELETE
                )
            );
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
