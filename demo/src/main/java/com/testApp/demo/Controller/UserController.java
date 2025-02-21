package com.testApp.demo.Controller;

import com.testApp.demo.Model.User;
import com.testApp.demo.Service.UserService;
import com.testApp.demo.Utils.LoginRequest;
import com.testApp.demo.Utils.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

    public LoginResponse login(@RequestBody LoginRequest loginRequest){

        return userService.verify(loginRequest);
    }


}
