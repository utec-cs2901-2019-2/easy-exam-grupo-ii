package com.easyexam.service;

import com.easyexam.model.User;
import com.easyexam.repository.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private IUserRepo userRepo;

    public List<User> findAll(){
        List<User> items = new ArrayList<>();

        for (User item :userRepo.findAll()) {
            items.add(item);
        }
        return items;
    }

    public User findOne(int id){
        return userRepo.findById(id).get();
    }

    public User create(User item){
        return userRepo.save(item);
    }

    public User update(User item){
        return userRepo.save(item);
    }

    public void delete(int id){
        userRepo.delete(findOne(id));
    }

    public User findUser(String email, String passwd){
        return userRepo.findUserByEmailAndPassword(email, passwd);
    }

    public User findOneByEmail(String email){
        return userRepo.findUserByEmail(email);
    }
}
