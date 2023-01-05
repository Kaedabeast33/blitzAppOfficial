package com.chipr.blitzApp.DTOs;

import com.chipr.blitzApp.Entities.Days;
import com.chipr.blitzApp.Entities.Events;
import com.chipr.blitzApp.Entities.Users;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class UserDto implements Serializable {
    private Long id;
    private String username;
    private String email;
    private String password;
    private Set<Days> availabilityDates=new HashSet<>();

    private Set<Events> eventsSet  =new HashSet<>();

    //GETTERS AND SETTERS

    public Set<Events> getEventsSet() {
        return eventsSet;
    }

    public void setEventsSet(Set<Events> eventsSet) {
        this.eventsSet = eventsSet;
    }

    public Set<Days> getAvailabilityDates() {
        return availabilityDates;
    }

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

    public void setAvailabilityDates(Set<Days> availabilityDates) {
        this.availabilityDates = availabilityDates;
    }
//CONSTRUCTOR

    public UserDto(Long id, String username, String email, String password, Set<Days> availabilityDates, Set<Events> eventsSet) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.availabilityDates = availabilityDates;
        this.eventsSet = eventsSet;
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
        if(user.getAvailabilityDates()!=null){
            this.availabilityDates=user.getAvailabilityDates();
        }
        if(user.getEventsSet()!=null){
            this.eventsSet=user.getEventsSet();
        }
    }
}
