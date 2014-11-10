package com.survey.mvc.dao;

import com.survey.mvc.entity.UsersEntity;

public interface UserDAO {
    public void addUser(UsersEntity user);
    public void updateUser(UsersEntity user);
    public UsersEntity getUser(int id);
}
