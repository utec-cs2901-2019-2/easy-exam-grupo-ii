package com.easyexam.service.impl;

import com.easyexam.repository.IRoleRepo;
import com.easyexam.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    IRoleRepo roleRepo;

}
