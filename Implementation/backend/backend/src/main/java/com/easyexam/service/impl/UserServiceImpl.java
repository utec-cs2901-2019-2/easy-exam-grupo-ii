package com.easyexam.service.impl;

import com.easyexam.model.Teacher;
import com.easyexam.model.User;
import com.easyexam.model.aux.UserCompleted;
import com.easyexam.repository.ICorrelativeRepo;
import com.easyexam.repository.ISuggestRepo;
import com.easyexam.repository.IUserRepo;
import com.easyexam.repository.ITeacherRepo;
import com.easyexam.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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

@Service (value = "userService")
public class UserServiceImpl implements IUserService, UserDetailsService {

    @Autowired
    IUserRepo userRepo;

    @Autowired
    ISuggestRepo suggestRepo;

    @Autowired
    ICorrelativeRepo correlativeRepo;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    @Autowired
    private ITeacherRepo teacherRepo;

    private static Logger LOG= LoggerFactory.getLogger(ProblemServiceImpl.class);

    /* Revisar */
    @Bean
    private List<SimpleGrantedAuthority> getAuthority() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User user = userRepo.findUserByEmail(username);
        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthority());
    }

    @Override
    public void delete(int id) {
        userRepo.deleteById(id);
    }

    @Override
    public User update(User user) {
        return userRepo.save(user);
    }

    @Override
    public User save(User user) {
        User newUser = new User();
        newUser.setId(correlativeRepo.getIdUser());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setActive(user.getActive());
        newUser.setRole(user.getRole());
        correlativeRepo.updateIdUser();
        return userRepo.save(newUser);
    }

    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        userRepo.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public User findUser(String email, String passwd) {
        return userRepo.findUserByEmailAndPassword(email, passwd);
    }

    @Override
    public User findByEmail(String username) {
        return userRepo.findUserByEmail(username);
    }

    @Override
    public User findById(int id) {
        Optional<User> optionalUser = userRepo.findById(id);
        return optionalUser.isPresent() ? optionalUser.get() : null;
    }

    @Override
    public User updatePassword(int id) {
        User user = findById(id);
        if(user != null) {
            user.setPassword(bcryptEncoder.encode(user.getPassword()));
            userRepo.save(user);
        }
        return user;
    }

    @Override
    public UserCompleted getDates(String email){
        UserCompleted user=new UserCompleted();
        user.setEmail(email);
        User u=userRepo.findUserByEmail(email);
        LOG.info("entro a serviece impl");
        LOG.info(u.getRole().getName());
        user.setId(u.getId());

        //user.setBonus(teacherRepo.getBonus(u.getId()));
        return user;
    }

    @Override
    public List<UserCompleted> getReportedUsers() {
        List<UserCompleted> users = new ArrayList<>();
        for (int i : suggestRepo.getSuggest()) {
            Teacher teacher = teacherRepo.findTeacherById(i);
            if (teacher.getUser().getActive()){
                users.add(new UserCompleted(teacher.getId(), teacher.getUser().getEmail(), suggestRepo.getCountByTeacherId(i),teacher.getFirstname(), teacher.getLastname()));
            }
        }
        return users;
    }

    @Override
    public void deleteSuggestById(int id) {
            
    }

}
