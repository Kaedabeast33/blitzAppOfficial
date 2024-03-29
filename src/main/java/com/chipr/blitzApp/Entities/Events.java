package com.chipr.blitzApp.Entities;

import com.chipr.blitzApp.DTOs.EventDto;
import jakarta.persistence.*;
import jdk.jfr.Event;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table()
public class Events {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

    @Column
    private String event_title;

    @Column
    private String event_info;

    @ManyToMany
    @JoinTable(
            name="event_dates",
            joinColumns = @JoinColumn(name="event_id"),
            inverseJoinColumns = @JoinColumn(name="date_id")
    )
    private Set<Days> day_set=new HashSet<>();
    @ManyToMany(mappedBy = "eventsSet")
    private Set<Users>event_users=new HashSet<>();

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
    public void addEventDates(Days date){
        this.day_set.add(date);
    }
    public void deleteEventDates(Days date){
        this.day_set.remove(date);
    }

    //CONSTRUCTOR
    public Events(Long id, String event_title, String event_info, Set<Days> day_set,Set<Users> event_users) {
        this.id = id;
        this.event_title = event_title;
        this.event_info = event_info;
        this.day_set = day_set;
        this.event_users = event_users;
    }
    public Events() {
    }
    public Events(EventDto eventDto){
        if(eventDto.getId()!=null){
            this.id=eventDto.getId();
        }if(eventDto.getEvent_title()!=null){
            this.event_title= eventDto.getEvent_title();
        }if(eventDto.getEvent_info()!=null){
            this.event_info=eventDto.getEvent_info();
        }if (eventDto.getDay_set() != null) {
            this.day_set = eventDto.getDay_set();
        }if(eventDto.getEvent_users()!=null){
            this.event_users=eventDto.getEvent_users();
        }
    }
}

//METHODS
