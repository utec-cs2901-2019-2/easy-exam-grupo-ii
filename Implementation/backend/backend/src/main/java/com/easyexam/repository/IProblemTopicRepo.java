package com.easyexam.repository;

import com.easyexam.model.ProblemTopic;
import com.easyexam.model.ProblemTopicId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProblemTopicRepo extends JpaRepository<ProblemTopic,ProblemTopicId> {

    @Override
    List<ProblemTopic> findAll();

}
