package com.survey.mvc.dao;

import com.survey.mvc.entity.AnswerOptionsEntity;

/**
 * Created by Belkin on 12.11.2014.
 */
public interface AnswerOptionsDAO {
    public void addAnswerOption(AnswerOptionsEntity answerOption);
    public void updateAnswerOption(AnswerOptionsEntity answerOption);
    public AnswerOptionsEntity getAnswerOption(int id);
    public void deleteAnswerOption(int id);
}
