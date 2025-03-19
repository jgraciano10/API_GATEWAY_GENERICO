package com.testApp.demo.Infrastructure.Adapters;


import com.testApp.demo.Domain.Models.In.LoginRequest;
import com.testApp.demo.Domain.Models.In.User;
import com.testApp.demo.Domain.Models.Out.LoginResponse;
import com.testApp.demo.Domain.Ports.AuthPort;
import com.testApp.demo.Domain.Ports.UserRepositoryPort;
import com.testApp.demo.Infrastructure.Config.Jwt.JwtService;
import com.testApp.demo.Infrastructure.Repositories.UserRepository.UserMappers.UserHomologador;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class AuthAdapter implements AuthPort {


    private final UserRepositoryPort userRepositoryPort;


    private final AuthenticationManager authenticationManager;


    private final JwtService jwtService;

    public AuthAdapter(UserRepositoryPort userRepositoryPort, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userRepositoryPort = userRepositoryPort;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }
    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUserName(), loginRequest.getPassword()
                    )
            );
            if(authentication.isAuthenticated()){
                User user = userRepositoryPort.findByEmail(loginRequest.getUserName());
                String jwt = jwtService.generateToken(user);
                return UserHomologador.UserToLoginResponse(user, jwt);
            }
            return UserHomologador.UserEntityToLoginResponse("Credenciales Incorrectas o expiradas", "", "");


        }catch(Exception e) {
            if(e.getMessage().equals("Bad credentials")){
                return UserHomologador.UserEntityToLoginResponse("Credenciales Incorrectas o expiradas", "","");
            }
            else{
                return UserHomologador.UserEntityToLoginResponse("Credenciales Incorrectas o expiradas", "","");
            }

        }
    }

    @Override
    public LoginResponse googleLogin(String name, String email) {
        try{
            User user = userRepositoryPort.findByEmail(email);
            if(user ==null){
                user = UserHomologador.googleLoginToUser(name, email);
                userRepositoryPort.createUser(user);
            }
            String jwt = jwtService.generateToken(email);
            return UserHomologador.UserEntityToLoginResponse(jwt, name, email);


        }catch(Exception e) {
            if(e.getMessage().equals("Bad credentials")){
                return UserHomologador.UserEntityToLoginResponse("Credenciales Incorrectas o expiradas", "","");
            }
            else{
                return UserHomologador.UserEntityToLoginResponse("Credenciales Incorrectas o expiradas", "","");
            }

        }
    }
}
