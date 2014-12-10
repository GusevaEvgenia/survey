package com.survey.mvc.service;

import com.survey.mvc.dao.AnswersDAO;
import com.survey.mvc.dao.QuestionsDAO;
import com.survey.mvc.model.analysis.data.AnalysisData;
import com.survey.mvc.model.analysis.data.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Belkin on 09.12.2014.
 */
@Service
@Transactional
public class AnalysisService {

    @Autowired
    private QuestionsDAO questionsDAO;
    @Autowired
    private AnswersDAO answersDAO;

    public ArrayList<AnalysisData> getAnalysisData(int idQuestion) {
        ArrayList<HashMap<String, String>> result = answersDAO.getAnalysisData(idQuestion);
        ArrayList<AnalysisData> data = new ArrayList<AnalysisData>();
        ArrayList<Answer> answers = new ArrayList<Answer>();
        for(HashMap r : result){
            answers.add(new Answer(
                    (Integer) r.get("idAnswer"),
                    (String)(r.get("answer") == null ? r.get("answerT") : r.get("answer")),
                    (Integer) r.get("aOrder")
            ));

        }
        data.add(new AnalysisData(answers, questionsDAO.getQuestion(idQuestion)));
        return data;
    }

    public ArrayList<AnalysisData> getAnalysisData(int[] ids) {
        ArrayList<AnalysisData> ad = new ArrayList<AnalysisData>();
        for(int id: ids){
            ad.addAll(getAnalysisData(id));
        }

        return ad;
    }
}
