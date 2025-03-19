package com.testApp.demo.Domain.Models.In;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Transient;

public class User {

    private Integer id;

    private String name;

    private String email;

    private String password;

    private String verificationCode;

    private boolean verified;


    private Role role;

    public User(){}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerificationCode() {
        return verificationCode;
    }
    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public boolean getVerfied() {
        return verified;
    }
    public void setVerified(Boolean verified) {
        this.verified = verified;
    }


    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
