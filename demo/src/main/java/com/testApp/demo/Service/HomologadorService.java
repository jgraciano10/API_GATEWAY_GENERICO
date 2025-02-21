package com.testApp.demo.Service;

import com.testApp.demo.Model.User;
import com.testApp.demo.Utils.LoginRequest;
import com.testApp.demo.Utils.LoginResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class HomologadorService {
    public static LoginResponse UserToLoginResponse(UserDetails user, String jwt){
        LoginResponse loginResponse = new LoginResponse(user, jwt);
        return loginResponse;
    }
}
