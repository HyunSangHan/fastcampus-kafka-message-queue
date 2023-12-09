package com.fastcampus.kafkahandson.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MyJpaRepository extends JpaRepository<MyEntity, Integer> { }
