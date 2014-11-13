package com.survey.mvc.service;

import com.survey.mvc.entity.AnswerOptionsEntity;

/**
 * Created by Belkin on 12.11.2014.
 */
public interface AnswerOptionsService {
    public void addAnswerOption(AnswerOptionsEntity answerOption);
    public void updateAnswerOption(AnswerOptionsEntity answerOption);
    public AnswerOptionsEntity getAnswerOption(int id);
    public void deleteAnswerOption(int id);
}
