package com.survey.mvc.service;

import com.survey.mvc.dao.QuestionsDAO;
import com.survey.mvc.entity.QuestionsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class QuestionsServiceImpl implements QuestionsService {

    @Autowired
    QuestionsDAO questionDAO;

    @Override
    public void addQuestion(QuestionsEntity question) {
        questionDAO.addQuestion(question);
    }

    @Override
    public void updateQuestion(QuestionsEntity question) {
        questionDAO.updateQuestion(question);
    }

    @Override
    public QuestionsEntity getQuestion(int id) {
        return questionDAO.getQuestion(id);
    }

    @Override
    public void deleteQuestion(int id) {
        questionDAO.deleteQuestion(id);
    }

    @Override
    public List<QuestionsEntity> getQuestions() {
        return questionDAO.getQuestions();
    }

    @SuppressWarnings("unchecked")
    public List<QuestionsEntity> getQuestionByForm(int id) {
        return questionDAO.getQuestionsByForm(id);
    }
}
