package com.testApp.demo.Config;


import com.testApp.demo.Repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
public class ApplicationConfig {

    private final UserRepository userRepository;

    public ApplicationConfig(UserRepository userRepository){
        this.userRepository  = userRepository;
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return username -> (UserDetails) userRepository.findByUserName(username);

    }
}
