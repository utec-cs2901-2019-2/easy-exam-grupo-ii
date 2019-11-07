package com.easyexam.service.impl;

import com.easyexam.repository.IAdminRepo;
import com.easyexam.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements IAdminService {

    @Autowired
    IAdminRepo adminRepository;


}
