package com.testApp.demo.Application;

import com.testApp.demo.Domain.Models.In.LoginRequest;
import com.testApp.demo.Domain.Models.Out.LoginResponse;
import com.testApp.demo.Domain.Ports.AuthPort;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthPort authPort;

    public AuthService(AuthPort authPort) {
        this.authPort = authPort;
    }

    public LoginResponse login(LoginRequest loginRequest) {
        return authPort.login(loginRequest);
    }

    public LoginResponse googleLogin(String name, String email) {
        return authPort.googleLogin(name, email);
    }


}
