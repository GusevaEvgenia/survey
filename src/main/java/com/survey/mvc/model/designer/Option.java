package com.survey.mvc.model.designer;

import com.survey.mvc.entity.AnswerOptionsEntity;

/**
 * Created by Belkin on 29.11.2014.
 */
public class Option {
    protected int idOption;
    protected String text;
    protected String textMatrix;

    public Option() {
    }

    public Option(AnswerOptionsEntity entity) {
        this.idOption = entity.getIdOption();
        this.text = entity.getText();
        this.textMatrix = entity.getTextMatrix();
    }

    public int getIdOption() {
        return idOption;
    }

    public void setIdOption(int idOption) {
        this.idOption = idOption;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTextMatrix() {
        return textMatrix;
    }

    public void setTextMatrix(String textMatrix) {
        this.textMatrix = textMatrix;
    }
}
