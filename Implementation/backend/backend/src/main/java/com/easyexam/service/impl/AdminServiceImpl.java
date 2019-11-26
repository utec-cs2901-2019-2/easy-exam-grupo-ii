package com.easyexam.service.impl;

import java.util.List;

import com.easyexam.model.Admin;
import com.easyexam.repository.IAdminRepo;
import com.easyexam.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements IAdminService {

    @Autowired
    IAdminRepo adminRepository;

    @Override
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    @Override
    public Admin save(Admin admin) {
        Admin newAdmin = new Admin();
        newAdmin.setFullname(admin.getFullname());
        newAdmin.setAddress(admin.getAddress());
        newAdmin.setPhone(admin.getPhone());
        newAdmin.setUser(admin.getUser());
        return adminRepository.save(newAdmin);
    }

}
