package com.survey.mvc.dao;

import com.survey.mvc.entity.AnswersEntity;
import com.survey.mvc.model.CompletedFormRow;
import com.survey.mvc.model.analysis.data.AnalysisData;

import java.util.ArrayList;
import java.util.HashMap;


public interface AnswersDAO {
    public void addAnswer(AnswersEntity answer);
    public AnswersEntity getAnswer(int id);
    public ArrayList<CompletedFormRow> getAnswersByCompletedForm(int id);
    public ArrayList<CompletedFormRow> getAnswersByCompletedForm(int id, String type);
    public ArrayList<CompletedFormRow> getAnswersByIdCompletedForm(int id, int idCompForm);
    public ArrayList<HashMap<String, String>> getAnalysisData(int id);
}
