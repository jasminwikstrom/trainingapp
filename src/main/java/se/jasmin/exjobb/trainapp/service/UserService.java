package se.jasmin.exjobb.trainapp.service;

import se.jasmin.exjobb.trainapp.api.dto.CreateNewUserDto;
import se.jasmin.exjobb.trainapp.repository.entity.User;


import java.util.List;



public interface UserService {
    User createUser(CreateNewUserDto createNewUserDto);

    List<User> getUsers(String username);



}