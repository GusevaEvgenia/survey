package com.survey.mvc.model.designer;

import com.survey.mvc.entity.AnswerOptionsEntity;
import com.survey.mvc.entity.QuestionsEntity;

/**
 * Created by Belkin on 29.11.2014.
 */
public class Question {
    protected int idQuestion;
    protected String text;
    protected int idType;
    protected Option[] options;
    protected int size;

    public Question() {
    }

    public Question(QuestionsEntity entity) {
        this.idQuestion = entity.getIdQuestion();
        this.text = entity.getText();
        this.idType = entity.getIdQtype();
        AnswerOptionsEntity[] entities = entity.getAnswerOptionsesByIdQuestion().toArray(new AnswerOptionsEntity[0]);
        Option[] options = new Option[entities.length];
        for (int i = 0; i < entities.length; i++) {
            options[i] = new Option(entities[i]);
        }

        this.options = options;
        this.size = options.length - 1;
    }

    public int getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(int idQuestion) {
        this.idQuestion = idQuestion;
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

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
