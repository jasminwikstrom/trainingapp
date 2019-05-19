package se.jasmin.exjobb.trainapp.service;


public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
