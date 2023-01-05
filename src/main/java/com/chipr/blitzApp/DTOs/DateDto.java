package com.chipr.blitzApp.DTOs;

import com.chipr.blitzApp.Entities.Days;
import com.chipr.blitzApp.Entities.Events;
import com.chipr.blitzApp.Entities.Users;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;

import java.util.HashSet;
import java.util.Set;

public class DateDto {
    private String date;

    private Set<Events> event_set=new HashSet<>();

    private Set<Users>day_users = new HashSet<>();

    public DateDto(String date, Set<Events> event_set, Set<Users> day_users) {
        this.date = date;
        this.event_set = event_set;
        this.day_users = day_users;
    }

    public DateDto() {
    }
    public DateDto(Days date){
        if(date.getDate()!=null){
            this.date=date.getDate();
        }if(date.getDay_users()!=null){
            this.day_users=date.getDay_users();
        }if(date.getEvent_set()!=null){
            this.event_set=date.getEvent_set();
        }
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Set<Events> getEvent_set() {
        return event_set;
    }

    public void setEvent_set(Set<Events> event_set) {
        this.event_set = event_set;
    }

    public Set<Users> getDay_users() {
        return day_users;
    }

    public void setDay_users(Set<Users> day_users) {
        this.day_users = day_users;
    }
}
