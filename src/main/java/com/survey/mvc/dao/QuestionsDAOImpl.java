package com.survey.mvc.dao;

import com.survey.mvc.entity.QuestionsEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuestionsDAOImpl implements QuestionsDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void addQuestion(QuestionsEntity question) {
        getCurrentSession().save(question);
    }

    @Override
    public void updateQuestion(QuestionsEntity question) {
        QuestionsEntity questionToUpdate = getQuestion(question.getIdQuestion());
        questionToUpdate.setText(question.getText());
        questionToUpdate.setScale(question.getScale());
        getCurrentSession().update(questionToUpdate);
    }

    @Override
    public QuestionsEntity getQuestion(int id) {
        QuestionsEntity question = (QuestionsEntity) getCurrentSession().get(QuestionsEntity.class, id);
        return question;
    }

    @Override
    public void deleteQuestion(int id) {
        QuestionsEntity question = getQuestion(id);
        if(question != null)
            getCurrentSession().delete(question);
    }

    @SuppressWarnings("unchecked")
    public List<QuestionsEntity> getQuestions() {
        return getCurrentSession().createQuery("from QuestionsEntity").list();
    }

    @SuppressWarnings("unchecked")
    public List<QuestionsEntity> getQuestionsByForm(int id) {
        return getCurrentSession().createQuery("from QuestionsEntity question where question.idForm = :id")
                .setInteger("id", id).list();
    }
}
