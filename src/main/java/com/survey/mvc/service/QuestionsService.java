package com.survey.mvc.service;


import com.survey.mvc.entity.QuestionsEntity;

import java.util.List;

public interface QuestionsService {
    public void addQuestion(QuestionsEntity question);
    public void updateQuestion(QuestionsEntity question);
    public QuestionsEntity getQuestion(int id);
    public void deleteQuestion(int id);
    public List<QuestionsEntity> getQuestions();
    public List<QuestionsEntity> getQuestionByForm(int id);
}
