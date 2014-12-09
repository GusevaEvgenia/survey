package com.survey.mvc.model;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by Belkin on 16.11.2014.
 */
public class CompletedFormRow {
    long id;
    String status;
    ArrayList<CompleteAnswer> answers = new ArrayList<CompleteAnswer>();

    public CompletedFormRow() {
    }

    public CompletedFormRow(long id, String status) {
        this();
        this.id = id;
        this.status = status;
    }

    public CompletedFormRow(long id, String status, ArrayList<CompleteAnswer> answers) {
        this(id, status);
        this.answers = answers;
    }

    public ArrayList<CompleteAnswer> getAnswers() {
        return answers;
    }

    public void putAnswer(CompleteAnswer answer) {
        answers.add( answer);
    }

    public long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public void answerSort(){
        answers.sort( new Comparator<CompleteAnswer>() {
            @Override
            public int compare(CompleteAnswer  answer1, CompleteAnswer  answer2) {
                return answer1.getOrder() - answer2.getOrder();
            }
        });
    }
}
