package com.testApp.demo.Service;

import com.testApp.demo.Model.CustomUserDetails;
import com.testApp.demo.Model.User;
import com.testApp.demo.Repository.UserRepository;
import com.testApp.demo.Utils.LoginRequest;
import com.testApp.demo.Utils.LoginResponse;
import org.springframework.jdbc.support.CustomSQLErrorCodesTranslation;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService{


    private final UserRepository userRepository;

    private final   BCryptPasswordEncoder bCryptPasswordEncoder;

    private final AuthenticationManager authenticationManager;

    private final CustomUserDetailsService customUserDetailsService;

    private final JwtService jwtService;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, AuthenticationManager authenticationManager, CustomUserDetailsService customUserDetailsService, JwtService jwtService) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authenticationManager = authenticationManager;
        this.customUserDetailsService = customUserDetailsService;
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


    public LoginResponse verify(LoginRequest loginRequest) {
        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUserName(), loginRequest.getPassword()
                    )
            );
            if(authentication.isAuthenticated()){
                //User user = userRepository.findByUserName(loginRequest.getUserName());
                UserDetails customUserDetails = customUserDetailsService.loadUserByUsername(loginRequest.getUserName());
                String jwt = jwtService.generateToken(customUserDetails);

                return HomologadorService.UserToLoginResponse(customUserDetails,jwt);
            }
            return HomologadorService.UserToLoginResponse(null, "Credenciales Incorrectas o expiradas");


        }catch(Exception e) {
            if(e.getMessage().equals("Bad credentials")){
                return HomologadorService.UserToLoginResponse(null, "Credenciales Incorrectas, intenta de nuevo");
            }
            else{
                return HomologadorService.UserToLoginResponse(null, "Internal server error");
            }

        }

    }
}
