package com.chipr.blitzApp.Service;

import com.chipr.blitzApp.DTOs.UserDto;
import com.chipr.blitzApp.Entities.Users;

import java.util.List;

public interface UsersService {
    List<Users> addUser(UserDto userDto);
    List<String>loginUser(UserDto userDto);
    List<String>updatePassword(UserDto userDto);
    List<String>updateEmail(UserDto userDto);

}
