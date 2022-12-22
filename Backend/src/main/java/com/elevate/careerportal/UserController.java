package com.elevate.careerportal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@RestController
@RequestMapping
public class UserController {
    @Autowired
    private UserDAO users;

    @Autowired
    JdbcTemplate template;

    @GetMapping(value="/")
    public String index() {
        return "Welcome to the PeopleShores CareerPortal!";
    }

    @GetMapping(value="/users/{id}")
    public User getUserById(@PathVariable int id) {
        return users.getById(id);
    }
    @GetMapping(value="/users")
    public Collection<User> getAllUsers() {

        return users.getAllUsers();
    }
    @GetMapping(value="/login")
    public User logIn()
    {
        //valid for email
        return users.getUser();
    }
    @PostMapping(value="/register")
    public User newUser(@RequestBody User users) {
        //validation for email input
        return this.users.addUsers(users);
    }

}
