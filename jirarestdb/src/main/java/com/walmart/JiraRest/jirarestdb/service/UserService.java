package com.walmart.JiraRest.jirarestdb.service;

import org.springframework.stereotype.Service;

import com.walmart.JiraRest.jirarestdb.domain.User;
import com.walmart.JiraRest.jirarestdb.repository.UserRepository;

import java.util.Collection;
import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Iterable<User> list() {
        return userRepository.findAll();
    }

    public Iterable<User> save(Collection<User> readValues) {
        return userRepository.saveAll(readValues);
    }

//    public User save(User user) {
//        return userRepository.save(user);
//    }
    public void save(List<User> users) {
        userRepository.saveAll(users);
    }
}
