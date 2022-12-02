package com.chipr.blitzApp.DTOs;

import com.chipr.blitzApp.Entities.Users;
import jakarta.persistence.Column;
import org.apache.catalina.User;

import java.io.Serializable;

public class UserDto implements Serializable {
    private Long id;
    private String username;
    private String email;
    private String password;

    //GETTERS AND SETTERS

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    //CONSTRUCTOR

    public UserDto(Long id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public UserDto() {
    }
    public UserDto(Users user){
        if(user.getUsername()!=null){
            this.username=user.getUsername();
        }
        if (user.getPassword() != null) {
            this.password=user.getPassword();
        }
        if (user.getEmail() != null) {
            this.email=user.getEmail();
        }
    }
}
