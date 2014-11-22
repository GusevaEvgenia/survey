package com.survey.mvc.dao;

import com.survey.mvc.entity.QuestionTypesEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuestionTypesDAOImpl implements QuestionTypesDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public QuestionTypesEntity getQuestionType(int id) {
        QuestionTypesEntity type = (QuestionTypesEntity) getCurrentSession().get(QuestionTypesEntity.class, id);
        return type;
    }

    @SuppressWarnings("unchecked")
    public List<QuestionTypesEntity> getQuestionTypes() {
        return getCurrentSession().createQuery("from QuestionTypesEntity").list();
    }
}
