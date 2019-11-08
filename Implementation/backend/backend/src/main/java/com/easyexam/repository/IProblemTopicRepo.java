package com.easyexam.repository;

import com.easyexam.model.ProblemTopic;
import com.easyexam.model.ProblemTopicId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProblemTopicRepo extends JpaRepository<ProblemTopic,ProblemTopicId> {

    @Override
    List<ProblemTopic> findAll();

}
