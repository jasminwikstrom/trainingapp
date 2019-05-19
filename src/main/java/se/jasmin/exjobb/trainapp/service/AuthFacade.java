package se.jasmin.exjobb.trainapp.service;

import se.jasmin.exjobb.trainapp.repository.entity.User;

public interface AuthFacade {
    User getLoggedInUser(String userName);
}
