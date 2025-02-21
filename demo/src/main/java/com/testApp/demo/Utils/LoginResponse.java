package com.testApp.demo.Utils;

import com.testApp.demo.Model.User;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

@Data
public class LoginResponse {
    private UserDetails user;
    private String jwt;

    public LoginResponse(UserDetails user, String jwt){
        this.user = user;
        this.jwt = jwt;
    }

    public UserDetails getUser() {
        return user;
    }

    public void setUser(UserDetails user) {
        this.user = user;
    }

    public String getJwt() {
        return this.jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
