package com.testApp.demo.Domain.Models.Out;

import lombok.Data;

@Data
public class LoginResponse {

    private String userName = "";
    private String email = "";
    private String jwt;

    public LoginResponse(String userName, String email, String jwt){
        this.userName = userName;
        this.email = email;
        this.jwt = jwt;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJwt() {
        return this.jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }


}
