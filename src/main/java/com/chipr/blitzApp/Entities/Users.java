package com.chipr.blitzApp.Entities;

import com.chipr.blitzApp.DTOs.UserDto;
import jakarta.persistence.*;

@Table
@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;

    @Column
    private String email;

    @Column
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

    //CONSTRUCTORS
    public Users(UserDto userDto) {
        if(userDto.getUsername()!=null){
            this.username=userDto.getUsername();
        }
        if(userDto.getPassword()!=null){
            this.password=userDto.getPassword();
        }
        if(userDto.getEmail()!=null){
            this.email=userDto.getEmail();
        }
    }

    public Users(Long id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Users() {
    }

    //METHODS
}
