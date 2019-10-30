package com.easyexam.repository;

import org.springframework.data.repository.CrudRepository;

import com.easyexam.model.User;

public interface IUserRepo extends CrudRepository<User, Long>{
    
}
