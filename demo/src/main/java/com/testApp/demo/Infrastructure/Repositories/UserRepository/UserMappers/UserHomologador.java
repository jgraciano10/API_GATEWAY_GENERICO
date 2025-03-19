package com.testApp.demo.Infrastructure.Repositories.UserRepository.UserMappers;

import com.testApp.demo.Domain.Models.In.Role;
import com.testApp.demo.Domain.Models.In.User;
import com.testApp.demo.Domain.Models.Out.LoginResponse;
import com.testApp.demo.Infrastructure.Repositories.Entities.UserEntity;
import org.springframework.stereotype.Service;

@Service
public class UserHomologador {
    public static LoginResponse UserEntityToLoginResponse(String jwt, String userName, String email){
        LoginResponse loginResponse = new LoginResponse(userName, email, jwt);
        return loginResponse;
    }

    public static LoginResponse UserEntityToLoginResponse(UserEntity userEntity, String jwt){
        LoginResponse loginResponse = new LoginResponse(userEntity.getName(), userEntity.getEmail(), jwt);
        return loginResponse;
    }

    public static LoginResponse UserToLoginResponse(User user, String jwt){
        LoginResponse loginResponse = new LoginResponse(user.getName(), user.getEmail(), jwt);
        return loginResponse;
    }

    public static User UserEntityToUser(UserEntity userEntity){
        User user= new User();
        user.setName(userEntity.getName());
        user.setEmail(userEntity.getEmail());
        user.setRole(userEntity.getRole());
        user.setVerified(userEntity.getVerfied());
        user.setVerificationCode(userEntity.getVerificationCode());
        user.setPassword(userEntity.getPassword());
        return user;
    }

    public static UserEntity UserToUserEntity(User user){
        UserEntity userEntity= new UserEntity();
        userEntity.setName(user.getName());
        userEntity.setEmail(user.getEmail());
        userEntity.setRole(user.getRole());
        userEntity.setVerified(user.getVerfied());
        userEntity.setVerificationCode(user.getVerificationCode());
        userEntity.setPassword(user.getPassword());
        return userEntity;
    }

    public static User googleLoginToUser(String name, String email){
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setRole(Role.USER);
        user.setVerified(true);
        user.setVerificationCode("sfnksnfkjnsdf");
        user.setPassword("");

        return user;

    }
}
