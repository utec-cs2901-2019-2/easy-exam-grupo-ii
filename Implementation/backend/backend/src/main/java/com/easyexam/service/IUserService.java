package com.easyexam.service;

import com.easyexam.model.User;
import com.easyexam.repository.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


public class IUserService implements UserDetailsService {

    @Autowired
    private IUserRepo userRepo;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findUserByEmail(username);
        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthority());
    }

    /* Revisar */ 
    private List<SimpleGrantedAuthority> getAuthority() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    public void delete(int id) {
        userRepo.deleteById(id);
    }

    public User update(User user) {
        return userRepo.save(user);
    }

    public User save(User user) {
        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setActive(user.getActive());
        return userRepo.save(newUser);
    }

    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        userRepo.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    public User findUser(String email, String passwd){
        return userRepo.findUserByEmailAndPassword(email, passwd);
    }

    public User findByEmail(String username) {
        return userRepo.findUserByEmail(username);
    }

    public User findById(int id) {
        Optional<User> optionalUser = userRepo.findById(id);
        return optionalUser.isPresent() ? optionalUser.get() : null;
    }

    public User updatePassword(int id) {
        User user = findById(id);
        if(user != null) {
            user.setPassword(bcryptEncoder.encode(user.getPassword()));
            userRepo.save(user);
        }
        return user;
    }
}
