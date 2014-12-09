package com.survey.mvc.model;

import java.util.ArrayList;

/**
 * Created by Belkin on 16.11.2014.
 */
public class CompleteAnswer {
    long idQuestion;
    int order;
    ArrayList<String> text = new ArrayList<String>();

    public CompleteAnswer() {
    }

    public CompleteAnswer(long idQuestion, int order) {
        this();
        this.idQuestion = idQuestion;
        this.order = order;
    }

    public CompleteAnswer(long idQuestion) {
        this();
        this.idQuestion = idQuestion;
    }

    public CompleteAnswer(long idQuestion, int order, ArrayList<String> text) {
        this(idQuestion, order);
        this.text = text;
    }

    public void putAnswer(String t) {
        text.add(t);
    }

    public long getIdQuestion() {
        return idQuestion;
    }

    public ArrayList<String> getText() {
        return text;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
