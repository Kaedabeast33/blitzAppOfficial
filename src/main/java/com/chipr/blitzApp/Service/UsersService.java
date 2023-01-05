package com.chipr.blitzApp.Service;

import com.chipr.blitzApp.DTOs.DateDto;
import com.chipr.blitzApp.DTOs.UserDto;
import com.chipr.blitzApp.Entities.Days;
import com.chipr.blitzApp.Entities.Users;

import java.util.List;

public interface UsersService {
    List<Users> addUser(UserDto userDto);
    List<String>loginUser(UserDto userDto);
    List<String>updatePassword(UserDto userDto);
    List<String>updateEmail(UserDto userDto);


    List<String> addAvailibilityDate(DateDto dateDto, Long userId);
    List<String> deleteAvailibilityDate(DateDto dateDto, Long userId);

    List<String> addToEvent(Long eventId, Long userId);
    List<String> deleteFromEvent(Long eventId, Long userId);
}
