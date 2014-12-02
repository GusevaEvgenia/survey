package com.survey.mvc.model.designer;

import com.survey.mvc.entity.FormsEntity;
import com.survey.mvc.entity.QuestionsEntity;
/**
 * Created by Belkin on 29.11.2014.
 */
public class Designer {
    protected Question[] questions;
    protected int size;

    public Designer() {
    }

    public Designer(FormsEntity entity) {
        QuestionsEntity[] entities = entity.getQuestionsesByIdForm().toArray(new QuestionsEntity[0]);
        Question[] questions = new Question[entities.length];
        for (int i = 0; i < entities.length; i++) {
            questions[i] = new Question(entities[i]);
        }

        this.questions = questions;
        this.size = questions.length - 1;
    }

    public Question[] getQuestions() {
        return questions;
    }

    public void setQuestions(Question[] questions) {
        this.questions = questions;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
