package com.survey.mvc.model.analysis.data;

import com.survey.mvc.model.interfaces.IQuestion;

import java.util.ArrayList;

/**
 * Created by Belkin on 05.12.2014.
 */
public class AnalysisData {
    IQuestion question;
    ArrayList<Answer> answers;

    public AnalysisData( ArrayList<Answer> answers, IQuestion question) {
        this();
        this.answers = answers;
        this.question = question;
    }

    public AnalysisData() {
    }

    public IQuestion getQuestion() {
        return question;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    public ArrayList<Answer> getAnswersWithOption(long id){
        ArrayList<Answer> response = new ArrayList<Answer>();

        for (Answer a: answers) {
            if(a.getId() == id) {
                response.add(a);
            }
        }

        return response;
    }
}
