package com.survey.mvc.service;

import com.survey.mvc.dao.AnswerOptionsDAO;
import com.survey.mvc.entity.AnswerOptionsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AnswerOptionsServiceImpl implements AnswerOptionsService {
    @Autowired
    private AnswerOptionsDAO answerOptionsDAO;

    @Override
    public void addAnswerOption(AnswerOptionsEntity answerOption) {
        answerOptionsDAO.addAnswerOption(answerOption);
    }

    @Override
    public void updateAnswerOption(AnswerOptionsEntity answerOption) {
        answerOptionsDAO.updateAnswerOption(answerOption);
    }

    @Override
    public AnswerOptionsEntity getAnswerOption(int id) {
        return answerOptionsDAO.getAnswerOption(id);
    }

    @Override
    public void deleteAnswerOption(int id) {
        answerOptionsDAO.deleteAnswerOption(id);
    }
}
