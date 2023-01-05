package com.chipr.blitzApp.Entities;

import com.chipr.blitzApp.DTOs.DateDto;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Table
@Entity
public class Days {
    @Id
    private String date;
    @Column
    @ManyToMany(mappedBy = "day_set")
    private Set<Events> event_set=new HashSet<>();
    @ManyToMany(mappedBy = "availabilityDates")
    private Set<Users>day_users = new HashSet<>();

    public Days(DateDto dateDto) {
        if(dateDto.getDate()!=null){
            this.date= dateDto.getDate();
        }if(dateDto.getDay_users()!= null) {
            this.day_users = dateDto.getDay_users();
        }if(dateDto.getEvent_set()!=null){
            this.event_set=dateDto.getEvent_set();
        }
    }

    //GETTERS AND SETTERS
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

    //CONSTRUCTORS


    public Days(String date, Set<Events> event_set, Set<Users> day_users) {
        this.date = date;
        this.event_set = event_set;
        this.day_users = day_users;
    }

    public Days(){
    }

    //METHODS
}
