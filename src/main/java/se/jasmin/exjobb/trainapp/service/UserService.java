package se.jasmin.exjobb.trainapp.service;

import se.jasmin.exjobb.trainapp.api.dto.CreateNewUserDto;
import se.jasmin.exjobb.trainapp.repository.entity.User;

import java.util.Optional;


public interface UserService {
    User createUser(CreateNewUserDto createNewUserDto);

    void save(User user);

    Optional<User> findByUsername(String username);

}