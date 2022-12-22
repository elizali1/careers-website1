package com.elevate.careerportal;

public class User {
    private int userId;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private boolean hiringManager;


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isHiringManager() {
        return hiringManager;
    }

    public void setHiringManager(boolean hiringManager) {
        this.hiringManager = hiringManager;
    }

    public User(int userId, String firstName, String lastName, String password, String email) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.hiringManager = hiringManager;
    }
}
