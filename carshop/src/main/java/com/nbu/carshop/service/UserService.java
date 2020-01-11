package com.nbu.carshop.service;

import com.nbu.carshop.entity.User;
import com.nbu.carshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers() {
        List<User> userList = new ArrayList();
        userRepository.findAll().forEach(user -> userList.add(user));

        return userList;
    }

    public User getUser(long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        return user;
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public void updateUser(long id, User user) {
        userRepository.save(user);
    }

    public void deleteUser(long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userRepository.delete(user);
    }
}
