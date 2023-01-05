package com.chipr.blitzApp.Service;

import com.chipr.blitzApp.DTOs.DateDto;
import com.chipr.blitzApp.DTOs.UserDto;
import com.chipr.blitzApp.Entities.Days;
import com.chipr.blitzApp.Entities.Events;
import com.chipr.blitzApp.Entities.Users;
import com.chipr.blitzApp.Repository.DaysRepository;
import com.chipr.blitzApp.Repository.EventsRepository;
import com.chipr.blitzApp.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    UsersRepository usersRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    DaysRepository daysRepository;
    @Autowired
    EventsRepository eventsRepository;

    @Override
    public List<Users> addUser(UserDto userDto) {
        Users user = new Users(userDto);
        if (usersRepository.findByUsername(user.getUsername()).isEmpty()) {
            usersRepository.saveAndFlush(user);
        }
        return usersRepository.findAll();
    }

    @Override
    public List<String> loginUser(UserDto userDto) {
        List<String> response = new ArrayList<>();
        Optional<Users> user = usersRepository.findByUsername(userDto.getUsername());
        if (user.isPresent()) {
            if (passwordEncoder.matches(userDto.getPassword(), user.get().getPassword())) {
                response.add("User " + user.get().getUsername() + " Logged in Successful");
//
            } else {
                response.add("Username or Password incorrect");
            }

        } else {
            response.add("Username or Password incorrect");
        }
        return response;
    }

    @Override
    public List<String> updatePassword(UserDto userDto) {
        Optional<Users> usersOptional = usersRepository.findByUsername(userDto.getUsername());
        List<String>response= new ArrayList<>();
        if(usersOptional.isPresent()){
            usersOptional.get().setPassword(userDto.getPassword());
            response.add("User " + usersOptional.get().getUsername()+"'s password is changed to " + userDto.getPassword());
            usersRepository.saveAndFlush(usersOptional.get());
        }else{
            response.add("Incorrect Credentials");
        }
        return response;
    }

    @Override
    public List<String> updateEmail(UserDto userDto) {
        Optional<Users> usersOptional = usersRepository.findByUsername(userDto.getUsername());
        List<String>response= new ArrayList<>();
        if(usersOptional.isPresent()){
            usersOptional.get().setEmail(userDto.getEmail());
            response.add("User " + usersOptional.get().getUsername()+"'s Email is changed to " + userDto.getEmail());
            usersRepository.saveAndFlush(usersOptional.get());
        }else{
            response.add("Incorrect Credentials");
        }
        return response;
    }

    @Override
    public List<String> addAvailibilityDate(DateDto dateDto, Long userId) {
        Optional<Users> usersOptional = usersRepository.findById(userId);
        Optional<Days> daysOptional = daysRepository.findById(dateDto.getDate());
        List<String> response = new ArrayList<>();
        if(usersOptional.isPresent()){
            if(daysOptional.isEmpty()){
                Days date = new Days(dateDto);
                daysRepository.saveAndFlush(date);
                usersOptional.get().addToAvailabilityDates(date);
                response.add("User: " + usersOptional.get().getUsername()+" added availability for "+ date);
                usersRepository.saveAndFlush(usersOptional.get());
            }
            else {
                Days date = daysOptional.get();
                usersOptional.get().addToAvailabilityDates(date);
                response.add("User: " + usersOptional.get().getUsername()+" added availability for "+ date.getDate());
                usersRepository.saveAndFlush(usersOptional.get());
            }
        }else{
            response.add("User doesn't exist");
        }
        return response;
    }

    @Override
    public List<String> deleteAvailibilityDate(DateDto dateDto, Long userId) {
        Optional<Users> usersOptional = usersRepository.findById(userId);
        Optional<Days> daysOptional = daysRepository.findById(dateDto.getDate());
        List<String> response = new ArrayList<>();
        if(usersOptional.isPresent()){
            if(!usersOptional.get().getAvailabilityDates().contains(daysOptional.get())){
                Days date = new Days(dateDto);
                response.add("User: " + usersOptional.get().getUsername()+" doesn't have a date set available for "+ date.getDate());

            }
            else {
                Days date = daysOptional.get();
                usersOptional.get().deleteFromAvailabilityDates(date);
                response.add("User: " + usersOptional.get().getUsername()+" removed availability for "+ date.getDate());
                usersRepository.saveAndFlush(usersOptional.get());
            }
        }else{
            response.add("User doesn't exist");
        }
        return response;
    }

    @Override
    public List<String> addToEvent(Long eventId, Long userId) {
        List<String> response = new ArrayList<>();
        Optional<Users> usersOptional = usersRepository.findById(userId);
        Optional<Events> eventsOptional = eventsRepository.findById(eventId);
        if(usersOptional.isPresent()){
            if(eventsOptional.isPresent()) {
                if (!usersOptional.get().getEventsSet().contains(eventsOptional.get())){
                    usersOptional.get().addToEvents(eventsOptional.get());
                    usersRepository.saveAndFlush(usersOptional.get());
                    response.add("event "+ eventsOptional.get().getEvent_title() + " is added");
                }else{
                    response.add("event is already added");
                }
            }else{
                response.add("event doesn't exist");
            }
        }else{
            response.add("User doesn't exist");
        }
        return response;
    }

    @Override
    public List<String> deleteFromEvent(Long eventId, Long userId) {
        List<String> response = new ArrayList<>();
        Optional<Users> usersOptional = usersRepository.findById(userId);
        Optional<Events> eventsOptional = eventsRepository.findById(eventId);
        if(usersOptional.isPresent()){
            if(eventsOptional.isPresent()) {
                if (usersOptional.get().getEventsSet().contains(eventsOptional.get())){
                    usersOptional.get().deleteFromEvents(eventsOptional.get());
                    usersRepository.saveAndFlush(usersOptional.get());
                    response.add("event "+ eventsOptional.get().getEvent_title() + " is deleted");
                }else{
                    response.add("event is already deleted ");
                }
            }else{
                response.add("event doesn't exist");
            }
        }else{
            response.add("User doesn't exist");
        }
        return response;
    }
}
