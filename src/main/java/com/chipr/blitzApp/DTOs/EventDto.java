package com.chipr.blitzApp.DTOs;

import com.chipr.blitzApp.Entities.Days;
import com.chipr.blitzApp.Entities.Events;
import com.chipr.blitzApp.Entities.Users;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

import java.util.HashSet;
import java.util.Set;

public class EventDto {
    private Long id;

    private String event_title;

    private String event_info;

    private Set<Days> day_set;

    private Set<Users> event_users;
    //GETTERS AND SETTERS

    public Set<Users> getEvent_users() {
        return event_users;
    }

    public void setEvent_users(Set<Users> event_users) {
        this.event_users = event_users;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getEvent_title() {
        return event_title;
    }
    public void setEvent_title(String event_title) {
        this.event_title = event_title;
    }
    public String getEvent_info() {
        return event_info;
    }
    public void setEvent_info(String event_info) {
        this.event_info = event_info;
    }
    public Set<Days> getDay_set() {
        return day_set;
    }
    public void setDay_set(Set<Days> day_set) {
        this.day_set = day_set;
    }

    //CONSTRUCTOR
    public EventDto(Long id, String event_title, String event_info, Set<Days> day_set, Set<Users>event_users) {
        this.id = id;
        this.event_title = event_title;
        this.event_info = event_info;
        this.day_set = day_set;
        this.event_users=event_users;
    }

    public EventDto() {
    }
    public EventDto(Events event){
        if(event.getId()!=null){
            this.id=event.getId();
        }if(event.getEvent_info()!=null){
            this.event_info=event.getEvent_info();
        }if(event.getEvent_title()!=null){
            this.event_title=event.getEvent_title();
        }if(event.getDay_set()!=null){
            this.day_set=event.getDay_set();
        }if(event.getEvent_users()!=null){
            this.event_users=event.getEvent_users();
        }

    }
}
