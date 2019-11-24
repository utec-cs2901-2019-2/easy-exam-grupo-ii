package com.easyexam.service.impl;

import com.easyexam.model.Role;
import com.easyexam.repository.IRoleRepo;
import com.easyexam.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    IRoleRepo roleRepo;

    @Override
    public Role findById(int id) {
        Optional<Role> optionalRole = roleRepo.findById(id);
        return optionalRole.isPresent() ? optionalRole.get() : null;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepo.findAll();
    }

    @Override
    public Role save(Role role) {
        return roleRepo.save(role);
    }


}
