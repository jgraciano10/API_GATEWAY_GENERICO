package com.testApp.demo.Application;

import com.testApp.demo.Domain.Models.In.User;
import com.testApp.demo.Domain.Ports.UserRepositoryPort;
import com.testApp.demo.Infrastructure.Repositories.UserRepository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService{

    private UserRepositoryPort userRepositoryPort;


    public UserService(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    public void createUser(User user){
        userRepositoryPort.createUser(user);
    }

    public User findUserByName(String Name){
        return userRepositoryPort.findByName(Name);
    }

    public User findUserByEmail(String email){
        return userRepositoryPort.findByEmail(email);
    }

    public List<User> getAllUsers(){
        return userRepositoryPort.getAllUsers();
    }

}
