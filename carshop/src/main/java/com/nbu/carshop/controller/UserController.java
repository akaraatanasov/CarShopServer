package com.nbu.carshop.controller;

import com.nbu.carshop.entity.User;
import com.nbu.carshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/users")
    List<User> getUsers() {
        return userService.getUsers();
    }

    @RequestMapping("/users/{id}")
    User getUser(@PathVariable("id") int id) {
        return userService.getUser(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/users")
    void addUser(@RequestBody User user) {
        userService.addUser(user);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/users/{id}")
    void updateUser(@PathVariable("id") int id, @RequestBody User user) {
        userService.updateUser(id, user);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/users/{id}")
    void deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
    }

}

