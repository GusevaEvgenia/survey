package com.survey.mvc.model.integration.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

/**
 * Created by Belkin on 07.12.2014.
 */
public class Form {
    protected int id;
    protected String title;
    protected String preview_url;
    protected HashMap<Long, Questions> questions;

    public Form() {
        questions = new HashMap<Long, Questions>();
    }

    public Form(int id, String title, String preview_url) {
        this();
        this.id = id;
        this.title = title;
        this.preview_url = preview_url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPreview_url() {
        return preview_url;
    }

    public void setPreview_url(String preview_url) {
        this.preview_url = preview_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public HashMap<Long, Questions> getQuestions() {
        return questions;
    }

    public void setQuestions(HashMap<Long, Questions> questions) {
        this.questions = questions;
    }

    public ArrayList<Questions> getSortedQuestion() {
        ArrayList<Questions> sortedQuestions = new ArrayList<Questions>(questions.values());
        sortedQuestions.sort( new Comparator<Questions>() {
            @Override
            public int compare(Questions  answer1, Questions  answer2) {
                return answer1.getOrder() - answer2.getOrder();
            }
        });
        return sortedQuestions;
    }

}
