package com.survey.mvc.dao;

import com.survey.mvc.entity.QuestionTypesEntity;

import java.util.List;

/**
 * Created by Belkin on 12.11.2014.
 */
public interface QuestionTypesDAO {
    public QuestionTypesEntity getQuestionType(int id);
    public List<QuestionTypesEntity> getQuestionTypes();
}


