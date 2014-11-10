package com.survey.mvc.service;

import com.survey.mvc.dao.UserDAO;

import com.survey.mvc.entity.UsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public void addUser(UsersEntity user) {
        userDAO.addUser(user);
    }

    @Override
    public void updateUser(UsersEntity user) {
        userDAO.updateUser(user);
    }

    @Override
    public UsersEntity getUser(int id) {
        return userDAO.getUser(id);
    }
}
