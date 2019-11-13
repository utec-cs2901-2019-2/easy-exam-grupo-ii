package com.easyexam.repository;

import com.easyexam.model.ProblemTopic;
import com.easyexam.model.ProblemTopicId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

@Repository
public interface IProblemTopicRepo extends JpaRepository<ProblemTopic,ProblemTopicId> {

    @Override
    List<ProblemTopic> findAll();

    @Query (value = "select problem.title, topics.name from problem, topics, problem_topic where id_problem = problem.id and id_topic = topics.id and problem.id = ?1", nativeQuery = true)
    List<ProblemTopic> getProblemTopics(int idProb);

}
