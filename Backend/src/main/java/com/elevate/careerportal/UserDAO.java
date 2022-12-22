package com.elevate.careerportal;

import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public interface UserDAO {
    Collection<User> getAllUsers();

    User addUsers(User users);

    User getById(int id);

    User getUser();
    User login(String email, String password);
//    boolean login(String email, String password);
}
