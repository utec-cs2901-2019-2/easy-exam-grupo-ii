package com.easyexam.service.impl;

import com.easyexam.repository.ISuggestRepo;
import com.easyexam.service.ISuggestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SuggestServiceImpl implements ISuggestService {

    @Autowired
    ISuggestRepo suggestRepo;

}
