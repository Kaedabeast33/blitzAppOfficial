package com.chipr.blitzApp.Entities;

import com.chipr.blitzApp.DTOs.UserDto;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

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

    @ManyToMany
    @JoinTable(
            name="availability_dates",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name = "date")
    )
    private Set<Days> availabilityDates = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name="user_events",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private Set<Events> eventsSet = new HashSet<>();


    //GETTERS AND SETTERS
    public void addToEvents(Events event){
        this.eventsSet.add(event);
    }
    public void deleteFromEvents(Events event){
        this.eventsSet.remove(event);
    }
    public Set<Events> getEventsSet() {
        return eventsSet;
    }

    public void setEventsSet(Set<Events> eventsSet) {
        this.eventsSet = eventsSet;
    }

    public Set<Days> getAvailabilityDates() {
        return availabilityDates;
    }
    public void setAvailabilityDates(Set<Days> availabilityDates) {
        this.availabilityDates = availabilityDates;
    }
    public void addToAvailabilityDates(Days availabilityDates){
        this.availabilityDates.add(availabilityDates);
    }
    public void deleteFromAvailabilityDates(Days availabilityDates){
        this.availabilityDates.remove(availabilityDates);
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

    //CONSTRUCTORS
    public Users(UserDto userDto) {
        if(userDto.getUsername()!=null){
            this.username=userDto.getUsername();
        }if(userDto.getPassword()!=null){
            this.password=userDto.getPassword();
        }if(userDto.getEmail()!=null){
            this.email=userDto.getEmail();
        }if(userDto.getAvailabilityDates()!=null){
            this.availabilityDates=userDto.getAvailabilityDates();
        }if(userDto.getEventsSet()!=null){
            this.eventsSet= userDto.getEventsSet();
        }
    }

    public Users(Long id, String username, String email, String password, Set<Days> availabilityDates, Set<Events> eventsSet) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.availabilityDates = availabilityDates;
        this.eventsSet = eventsSet;
    }

    public Users() {
    }

    //METHODS
}
