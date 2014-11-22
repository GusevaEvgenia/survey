package com.survey.mvc.dao;

import com.survey.mvc.entity.AnswerOptionsEntity;
import com.survey.mvc.entity.AnswersEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AnswerOptionsDAOImpl implements AnswerOptionsDAO {
    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void addAnswerOption(AnswerOptionsEntity answerOption) {
        getCurrentSession().save(answerOption);
    }

    @Override
    public void updateAnswerOption(AnswerOptionsEntity answerOption) {
        AnswerOptionsEntity answerOptionToUpdate = getAnswerOption(answerOption.getIdOption());
        answerOptionToUpdate.setText(answerOption.getText());
        getCurrentSession().update(answerOptionToUpdate);
    }

    @Override
    public AnswerOptionsEntity getAnswerOption(int id) {
        AnswerOptionsEntity answerOption = (AnswerOptionsEntity) getCurrentSession().get(AnswersEntity.class, id);
        return answerOption;
    }

    @Override
    public void deleteAnswerOption(int id) {
        getCurrentSession().delete(id);
    }
}
