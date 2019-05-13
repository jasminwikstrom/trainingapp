package se.jasmin.exjobb.trainapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.jasmin.exjobb.trainapp.api.dto.CreateNewUserDto;
import se.jasmin.exjobb.trainapp.repository.UserRepository;
import se.jasmin.exjobb.trainapp.repository.entity.User;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(CreateNewUserDto createNewUserDto) {

        if (createNewUserDto.getName() == null) {
            throw new RuntimeException("Title can not be null");
        }

        var newUser = new User();
        newUser.setUsername(createNewUserDto.getUsername());
        newUser.setName(createNewUserDto.getName());

        newUser.setGoals(createNewUserDto.getGoals());
        newUser.setWeightTrackList(List.of());

        return userRepository.save(newUser);
    }

    @Override
    public List<User> getUsers(String username) {

        List<User> users = userRepository.findByQuery(username);
        return users;
    }
}


