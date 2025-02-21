package com.testApp.demo.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@Table(name = "users")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "verification_code")
    private String verificationCode;
    @Column(name = "verified")
    private boolean verified;

    @Enumerated(EnumType.STRING) // Guarda el nombre del enum como texto
    @Column(nullable = false)
    private Role role;

    @Transient
    private String jwt;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
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

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }


}
