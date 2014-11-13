package com.survey.mvc.service;

import com.survey.mvc.dao.AnswersDAO;
import com.survey.mvc.entity.AnswersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AnswersServiceImpl implements AnswersService {
    @Autowired
    private AnswersDAO answerDAO;

    @Override
    public void addAnswer(AnswersEntity answer) {
        answerDAO.addAnswer(answer);
    }

    @Override
    public AnswersEntity getAnswer(int id) {
        return answerDAO.getAnswer(id);
    }
}
