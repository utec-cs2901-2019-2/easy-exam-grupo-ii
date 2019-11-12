package com.easyexam.service.impl;

import com.easyexam.model.Topic;
import com.easyexam.repository.IProblemTopicRepo;
import com.easyexam.repository.ITopicRepo;
import com.easyexam.service.ITopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicServiceImpl implements ITopicService {

    @Autowired
    ITopicRepo topicRepo;

    @Autowired
    IProblemTopicRepo problemTopicRepo;

    @Override
    public List<Topic> getAllTopics() {
        return topicRepo.findAll();
    }

}
