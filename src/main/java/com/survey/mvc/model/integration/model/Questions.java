package com.survey.mvc.model.integration.model;

import java.util.HashMap;

/**
 * Created by Belkin on 07.12.2014.
 */
public class Questions {
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
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
