package com.survey.mvc.model;

/**
 * Created by Belkin on 16.11.2014.
 */
public class CompleteAnswer {
    int idQuestion;
    int order;
    String text;

    public CompleteAnswer(int idQuestion, int order, String text) {
        this(text);
        this.idQuestion = idQuestion;
        this.order = order;
    }

    public CompleteAnswer(String text) {
        this.text = text;
    }

    public int getIdQuestion() {
        return idQuestion;
    }

    public String getText() {
        return text;
    }

    public int getOrder() {
        return order;
    }


}
