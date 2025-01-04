package com.testApp.demo.Controller;

import com.testApp.demo.Model.User;
import com.testApp.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLOutput;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/get-all-users")


    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/create-user")

    public void createUser(@RequestBody User user){

        userService.createUser(user);
    }

    @PostMapping("/login")

    public String login(@RequestBody User user){

        return userService.verify(user);
    }


}
