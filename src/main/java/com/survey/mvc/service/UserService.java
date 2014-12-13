package com.survey.mvc.service;

import com.survey.mvc.entity.UsersEntity;

public interface UserService {
    public void addUser(UsersEntity user);
    public void updateUser(UsersEntity user);
    public UsersEntity getUser(int id);
    public UsersEntity tryLogin(String username, String password);
}
