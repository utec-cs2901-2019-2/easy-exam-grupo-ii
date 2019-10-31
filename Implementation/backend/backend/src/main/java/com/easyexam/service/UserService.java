package com.easyexam.service;

import com.easyexam.model.User;
import com.easyexam.repository.IUserRepo;
import com.easyexam.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service(value = "userService")
public class UserService implements UserDetailsService {

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

    private List<SimpleGrantedAuthority> getAuthority() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        userRepo.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    public void delete(int id) {
        userRepo.deleteById(id);
    }

    public User findOne(String username) {
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

    public User update(User userDto) {
        User user = findById(userDto.getId());
        if(user != null) {
            BeanUtils.copyProperties(userDto, user, "password");
            userRepo.save(user);
        }
        return userDto;
    }

    public User save(User user) {
        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setActivate(user.getActivate());
        return userRepo.save(newUser);
    }
}
