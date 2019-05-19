package se.jasmin.exjobb.trainapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.jasmin.exjobb.trainapp.repository.entity.User;

@Component
public class AuthFacadeImpl implements AuthFacade {

    @Autowired
    private UserService userService;

    @Override
    public User getLoggedInUser(String userName) {
        var user = userService.findByUsername(userName);

        if (user.isEmpty()) {
            throw new RuntimeException("user does not exist");
        }

        return user.get();

    }
}

