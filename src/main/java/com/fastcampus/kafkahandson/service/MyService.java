package com.fastcampus.kafkahandson.service;

import com.fastcampus.kafkahandson.model.MyModel;

import java.util.List;

public interface MyService {

    public List<MyModel> findAll();
    public MyModel findById(Integer id);
    public MyModel save(MyModel model);
    public void delete(Integer id);
}
