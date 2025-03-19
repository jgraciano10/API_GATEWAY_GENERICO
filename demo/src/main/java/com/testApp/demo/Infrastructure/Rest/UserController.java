package com.testApp.demo.Infrastructure.Rest;

import com.testApp.demo.Domain.Models.In.User;
import com.testApp.demo.Infrastructure.Repositories.Entities.UserEntity;
import com.testApp.demo.Application.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/get-all-users")

    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/findByUserName")

    public User findByUserName(@RequestParam String userName){
        return userService.findUserByName(userName);
    }

    @PostMapping("/create-user")

    public ResponseEntity<?> createUser(@RequestBody User user){
        if(user.getPassword().equals("") || user.getName().equals("") || user.getEmail().equals("")){
            return ResponseEntity.badRequest().build();
        }
        try{
            userService.createUser(user);
            return ResponseEntity.ok().build();
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
        
    }


}
