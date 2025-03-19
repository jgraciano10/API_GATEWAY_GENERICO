package com.testApp.demo.Infrastructure.Rest;

import com.testApp.demo.Application.AuthService;
//import com.testApp.demo.Service.UserService;


import com.testApp.demo.Domain.Models.Out.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;
import com.testApp.demo.Domain.Models.In.LoginRequest;

@RestController
@RequestMapping("/auth")

public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")

    public ResponseEntity<?> login(@RequestBody LoginRequest  loginRequest){

        if(loginRequest.getPassword().equals("") || loginRequest.getUserName().equals("")){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @GetMapping("/google")

    public LoginResponse googleLogin(@AuthenticationPrincipal OAuth2User principal){

        try{
            String name = principal.getAttribute("name");
            String email = principal.getAttribute("email");
            return authService.googleLogin(name, email);
        }catch (Exception e){
            return null;
        }

    }

}
