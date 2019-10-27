package com.easyexam.repository;

import com.easyexam.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITopicRepo extends JpaRepository<Topic,Integer> {
}
