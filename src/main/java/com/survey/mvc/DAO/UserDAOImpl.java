package com.survey.mvc.dao;

import com.survey.mvc.entity.UsersEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void addUser(UsersEntity user) {
        getCurrentSession().save(user);
    }

    @Override
    public void updateUser(UsersEntity user) {
        UsersEntity userToUpdate = getUser(user.getIdUser());
        userToUpdate.setName(user.getName());
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setPassword(user.getPassword());
        getCurrentSession().update(userToUpdate);
    }

    @Override
    public UsersEntity getUser(int id) {
        UsersEntity user = (UsersEntity) getCurrentSession().get(UsersEntity.class, id);
        return user;
    }
}
