package se.jasmin.exjobb.trainapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import se.jasmin.exjobb.trainapp.api.dto.CreateNewUserDto;
import se.jasmin.exjobb.trainapp.repository.RoleRepository;
import se.jasmin.exjobb.trainapp.repository.UserRepository;
import se.jasmin.exjobb.trainapp.repository.entity.User;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(roleRepository.findAll()));
        userRepository.save(user);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

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
}



