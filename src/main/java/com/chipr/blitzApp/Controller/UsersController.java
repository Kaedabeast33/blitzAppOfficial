package com.chipr.blitzApp.Controller;

import com.chipr.blitzApp.DTOs.DateDto;
import com.chipr.blitzApp.DTOs.UserDto;
import com.chipr.blitzApp.Entities.Users;
import com.chipr.blitzApp.Repository.UsersRepository;
import com.chipr.blitzApp.Service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UsersService usersService;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/addUser")
    public List<String> addUser(@RequestBody UserDto userDto){
        List<String> response = new ArrayList<>();
        String passHash=passwordEncoder.encode(userDto.getPassword());

        userDto.setPassword(passHash);
        usersService.addUser(userDto);
        response.add("user Added Successfully");
        response.add(usersService.addUser(userDto).toString());
        response.add(userDto.getUsername() + " "+userDto.getPassword());

        return response;
    }
    @PostMapping("/login")
    public List<String> userLogin(@RequestBody UserDto userDto){
        return usersService.loginUser(userDto);
    }
    @PutMapping("/updatePassword")
    public List<String> updatePassword(@RequestBody UserDto password){
        String passHash=passwordEncoder.encode(password.getPassword());
        password.setPassword(passHash);
        return usersService.updatePassword(password);
    }
    @PutMapping("/updateEmail")
    public List<String> updateEmail(@RequestBody UserDto email){
        return usersService.updateEmail(email);
    }
    @PutMapping("/{userId}/addAvailability")
    public List<String> addAvailability(@PathVariable Long userId,@RequestBody DateDto dateDto){
        return usersService.addAvailibilityDate(dateDto,userId);
    }
    @DeleteMapping("/{userId}/deleteAvailability")
    public List<String> deleteAvailability(@PathVariable Long userId,@RequestBody DateDto dateDto){
        return usersService.deleteAvailibilityDate(dateDto,userId);
    }
    @PutMapping("/{userId}/addEvent/{eventId}")
            public List<String> addToEvent(@PathVariable Long userId, @PathVariable Long eventId){
        return usersService.addToEvent(eventId,userId);
    }
    @DeleteMapping("/{userId}/deleteEvent/{eventId}")
    public List<String> deleteFromEvent(@PathVariable Long userId, @PathVariable Long eventId){
        return usersService.deleteFromEvent(eventId,userId);
    }
}
