package com.testApp.demo.Domain.Ports;
import com.testApp.demo.Domain.Models.Out.*;
import com.testApp.demo.Domain.Models.In.LoginRequest;

public interface AuthPort {

   LoginResponse login(LoginRequest loginRequest);

   LoginResponse googleLogin(String name, String email);
}
