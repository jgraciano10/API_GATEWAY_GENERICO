package com.testApp.demo.Domain.Ports;

import com.testApp.demo.Domain.Models.In.User;

import java.util.List;

public interface UserRepositoryPort {
    User findByName(String name);
    User findByEmail(String email);

    Boolean createUser(User user);
    List<User> getAllUsers();
}
