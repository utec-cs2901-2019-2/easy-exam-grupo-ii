package com.easyexam.controller;

import com.easyexam.model.Topic;
import com.easyexam.model.aux.ApiResponse;
import com.easyexam.model.aux.UserCompleted;
import com.easyexam.repository.ITopicRepo;
import com.easyexam.service.ITopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/topics/v1")
public class TopicController {

    @Autowired
    ITopicService topicService;

    @GetMapping("/topics/getTopics")
    public List<Topic> getAllTopics(){
        return topicService.getAllTopics();
    }

    @PostMapping("/topics/getTopics")
    public List<Topic> createTopic(){
        return topicService.getAllTopics();
    }
}
