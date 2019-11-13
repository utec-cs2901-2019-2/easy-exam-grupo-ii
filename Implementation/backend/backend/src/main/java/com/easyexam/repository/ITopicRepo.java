package com.easyexam.repository;

import com.easyexam.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITopicRepo extends JpaRepository<Topic,Integer> {

    @Override
    List<Topic> findAll();

    Topic findTopicById(int id);

}
