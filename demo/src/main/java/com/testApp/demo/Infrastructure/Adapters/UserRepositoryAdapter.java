package com.testApp.demo.Infrastructure.Adapters;

import com.testApp.demo.Domain.Models.In.User;
import com.testApp.demo.Domain.Ports.UserRepositoryPort;
import com.testApp.demo.Infrastructure.Repositories.Entities.UserEntity;
import com.testApp.demo.Infrastructure.Repositories.UserRepository.UserMappers.UserHomologador;
import com.testApp.demo.Infrastructure.Repositories.UserRepository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserRepositoryAdapter implements UserRepositoryPort {

    private UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public  UserRepositoryAdapter(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User findByName(String name) {
        try{
           UserEntity userEntity = userRepository.findByName(name);
           return UserHomologador.UserEntityToUser(userEntity);
        }catch (Exception e){
            throw e;
        }

    }

    @Override
    public User findByEmail(String email) {
        try{
            UserEntity userEntity = userRepository.findByEmail(email);
            return UserHomologador.UserEntityToUser(userEntity);
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public Boolean createUser(User user) {
        try{
            UserEntity userEntity = UserHomologador.UserToUserEntity(user);
            userEntity.setPassword(bCryptPasswordEncoder.encode(userEntity.getPassword()));
            userRepository.save(userEntity);
            return true;
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public List<User> getAllUsers() {
        try{
            List<UserEntity> userEntitiesList = userRepository.findAll();
            List<User> userList= userEntitiesList.stream().map(UserHomologador::UserEntityToUser).collect(Collectors.toList());
            return userList;
        }catch (Exception e){
            throw e;
        }
    }
}
