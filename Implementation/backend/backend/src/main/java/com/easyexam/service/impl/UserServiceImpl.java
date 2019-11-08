package com.easyexam.service.impl;

import com.easyexam.repository.IUserRepo;
import com.easyexam.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    IUserRepo userRepo;
}
