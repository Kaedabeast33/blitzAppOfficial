package com.chipr.blitzApp.Service;

import com.chipr.blitzApp.DTOs.UserDto;
import com.chipr.blitzApp.Entities.Users;
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
//            if (passwordEncoder.matches(userDto.getPassword(), user.get().getPassword())) {
                response.add("User " + user.get().getUsername() + " Logged in Successful");
//
//            } else {
//                response.add("Username or Password incorrect");
//            }

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

}
