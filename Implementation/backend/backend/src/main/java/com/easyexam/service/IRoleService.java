package com.easyexam.service;

import com.easyexam.model.Role;

import java.util.List;

public interface IRoleService {
    Role findById(int id);

    List<Role> getAllRoles();

    Role save(Role role);
}
