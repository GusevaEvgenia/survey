package com.survey.mvc.model.designer;

/**
 * Created by Belkin on 29.11.2014.
 */
public class Question {
    protected String text;
    protected int idType;
    protected Option[] options;

    public Question() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    public Option[] getOptions() {
        return options;
    }

    public void setOptions(Option[] options) {
        this.options = options;
    }
}
