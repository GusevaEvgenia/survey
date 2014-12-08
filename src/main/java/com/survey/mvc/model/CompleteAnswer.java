package com.survey.mvc.model;

import java.util.ArrayList;

/**
 * Created by Belkin on 16.11.2014.
 */
public class CompleteAnswer {
    int idQuestion;
    int order;
    ArrayList<String> text;

    public CompleteAnswer() {
        text = new ArrayList<String>();
    }

    public CompleteAnswer(int idQuestion, int order) {
        this();
        this.idQuestion = idQuestion;
        this.order = order;
    }

    public CompleteAnswer(int idQuestion, int order, ArrayList<String> text) {
        this(idQuestion, order);
        this.text = text;
    }

    public void putAnswer(String t) {
        text.add(t);
    }

    public int getIdQuestion() {
        return idQuestion;
    }

    public ArrayList<String> getText() {
        return text;
    }

    public int getOrder() {
        return order;
    }


}
