package com.survey.mvc.service;

import com.survey.mvc.entity.AnswersEntity;
import com.survey.mvc.model.CompletedFormRow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Belkin on 12.11.2014.
 */
public interface AnswersService {
    public void addAnswer(AnswersEntity answer);
    public AnswersEntity getAnswer(int id);
    public ArrayList<CompletedFormRow> getAnswersByCompletedForm(int id);
    public ArrayList<CompletedFormRow> getAnswersByCompletedForm(int id, String type);
    public ArrayList<CompletedFormRow> getAnswersByIdCompletedForm(int id, int idCompForm);

    public HashMap<String, String> getStatuses();
}
