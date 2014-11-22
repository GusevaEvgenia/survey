package com.survey.mvc.dao;


import com.survey.mvc.entity.AnswersEntity;
import com.survey.mvc.entity.QuestionsEntity;

import java.util.List;

public interface QuestionsDAO {
    public void addQuestion(QuestionsEntity question);
    public void updateQuestion(QuestionsEntity question);
    public QuestionsEntity getQuestion(int id);
    public void deleteQuestion(int id);
    public List<QuestionsEntity> getQuestions();
    public List<QuestionsEntity> getQuestionsByForm(int id);
}
