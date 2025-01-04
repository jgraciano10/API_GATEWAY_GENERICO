package com.testApp.demo.Service;

import com.testApp.demo.Model.CustomUserDetails;
import com.testApp.demo.Model.User;
import com.testApp.demo.Repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository repository;

    public CustomUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = repository.findByUserName(username);
        if(!Objects.isNull(user)){
            return new CustomUserDetails(user);
        }
        else{
            throw new UsernameNotFoundException("User not found");
        }
    }
}
