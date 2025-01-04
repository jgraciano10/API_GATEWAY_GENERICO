package com.testApp.demo.Service;

import com.testApp.demo.Model.User;
import com.testApp.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService{


    private final UserRepository userRepository;

    private final   BCryptPasswordEncoder bCryptPasswordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public void createUser(User user){
        try {
            userRepository.save(user);
        } catch (Exception e) {
            System.out.println("Error al guardar el usuario: " + e.getMessage());
            throw e; // Vuelve a lanzar la excepción si es necesario
        }
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }


    public String verify(User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUserName(), user.getPassword()
                )
        );
       if(authentication.isAuthenticated()){
           return jwtService.generateToken(user);
       }
       return "Failure";
    }
}
