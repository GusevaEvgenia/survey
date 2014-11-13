package com.survey.mvc.service;

import com.survey.mvc.entity.AnswersEntity;

/**
 * Created by Belkin on 12.11.2014.
 */
public interface AnswersService {
    public void addAnswer(AnswersEntity answer);
    public AnswersEntity getAnswer(int id);
}
