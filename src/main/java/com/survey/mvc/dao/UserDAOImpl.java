package com.survey.mvc.dao;

import com.survey.mvc.entity.UsersEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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
        userToUpdate.setToken(user.getToken());
        getCurrentSession().update(userToUpdate);
    }

    @SuppressWarnings("unchecked")
    public UsersEntity getUser(int id) {
        UsersEntity user = (UsersEntity) getCurrentSession().get(UsersEntity.class, id);
        return user;
    }
    @SuppressWarnings("unchecked")
    public UsersEntity getUser(String username, String password) {
        List<UsersEntity> users = getCurrentSession().
                createQuery("from UsersEntity where login = :username and password like :pass")
                .setString("username", username)
                .setString("pass", password).list();

        if(users.isEmpty()) {
            return null;
        } else {
            return users.get(0);
        }
    }
}
