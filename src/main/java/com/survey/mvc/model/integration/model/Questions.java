package com.survey.mvc.model.integration.model;

import com.survey.mvc.model.interfaces.IQuestion;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Belkin on 07.12.2014.
 */
public class Questions implements IQuestion{
    protected long id;
    protected String text;
    protected int order;
    protected HashMap<Long, String> options;

    public Questions() {
        options = new HashMap<Long, String>();
    }

    public Questions(long id, String text) {
        this();
        this.id = id;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    @Override
    public ArrayList<HashMap<String, String>> getAnswers() {
        ArrayList<HashMap<String, String>> answers = new ArrayList<HashMap<String, String>>();
        for (Long answerId: options.keySet()) {
            HashMap<String, String> row = new HashMap<String, String>();
            row.put("id", String.valueOf(answerId));
            row.put("text", options.get(answerId));
//            row.put("order", options.get(answerId));TODO add order
            answers.add(row);
        }
        return answers;
    }

    @Override
    public String getScale() {
        return "unknown";
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public HashMap<Long, String> getOptions() {
        return options;
    }

    public void setOptions(HashMap<Long, String> options) {
        this.options = options;
    }
}
