package com.easyexam.service.impl;

import com.easyexam.repository.IAuditRepo;
import com.easyexam.service.IAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuditServiceImpl implements IAuditService{

    @Autowired
    IAuditRepo auditRepo;
}
