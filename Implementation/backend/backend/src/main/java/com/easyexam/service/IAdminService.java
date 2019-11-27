package com.easyexam.service;

import java.util.List;

import com.easyexam.model.Admin;

public interface IAdminService {
    public List<Admin> getAllAdmins();

    public Admin save(Admin admin);
}
