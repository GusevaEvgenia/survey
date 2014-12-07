package com.survey.mvc.model;

import java.util.ArrayList;

/**
 * Created by Belkin on 16.11.2014.
 */
public class CompletedFormRow {
    int id;
    String status;
    ArrayList<CompleteAnswer> answers = new ArrayList<CompleteAnswer>();

    public CompletedFormRow() {
    }

    public CompletedFormRow(int id, String status) {
        this();
        this.id = id;
        this.status = status;
    }

    public CompletedFormRow(int id, String status, ArrayList<CompleteAnswer> answers) {
        this(id, status);
        this.answers = answers;
    }

    public ArrayList<CompleteAnswer> getAnswers() {
        return answers;
    }

    public void putAnswer(CompleteAnswer answer) {
        answers.add( answer);
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }
}
