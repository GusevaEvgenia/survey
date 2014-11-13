package com.survey.mvc.service;

import com.survey.mvc.entity.QuestionTypesEntity;

import java.util.List;

public interface QuestionTypesService {
    public QuestionTypesEntity getQuestionType(int id);
    public List<QuestionTypesEntity> getQuestionTypes();
}
