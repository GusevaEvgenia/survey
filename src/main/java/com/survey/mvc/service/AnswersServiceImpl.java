package com.survey.mvc.service;

import com.survey.mvc.dao.AnswersDAO;
import com.survey.mvc.entity.AnswersEntity;
import com.survey.mvc.model.CompletedFormRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    @SuppressWarnings("unchecked")
    public ArrayList<CompletedFormRow> getAnswersByCompletedForm(int id) {
        return answerDAO.getAnswersByCompletedForm(id);
    }

    @Override
    public ArrayList<CompletedFormRow> getAnswersByCompletedForm(int id, String type) {
        return answerDAO.getAnswersByCompletedForm(id, type);
    }

    @Override
    public ArrayList<CompletedFormRow> getAnswersByIdCompletedForm(int id, int idCompForm) {
        return answerDAO.getAnswersByIdCompletedForm(id, idCompForm);
    }

    @Override
    public HashMap<String, String> getStatuses() {
        HashMap<String, String> statuses = new HashMap<String, String>();
        statuses.put("all", "Все ответы");
        statuses.put("new", "Новый");
        statuses.put("viewed", "Просмотренный");
        return statuses;
    }

}
