package com.chipr.blitzApp.Entities;

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

    //CONSTRUCTORS
    public Days(String date, Set<Events> event_set) {
        this.date = date;
        this.event_set = event_set;
    }
    public Days(){
    }

    //METHODS
}
