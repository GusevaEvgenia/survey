package com.survey.mvc.model.designer;

import com.survey.mvc.entity.FormsEntity;
import com.survey.mvc.entity.QuestionsEntity;
/**
 * Created by Belkin on 29.11.2014.
 */
public class Designer {
    protected Question[] questions;

    public Designer() {
    }

    public Designer(FormsEntity entity) {
        this();
        QuestionsEntity[] entities = entity.getQuestionsesByIdForm().toArray(new QuestionsEntity[0]);
        Question[] questions = new Question[entities.length];
        for (int i = 0; i < entities.length; i++) {
            questions[i] = new Question(entities[i]);
        }

        this.questions = questions;
    }

    public Question[] getQuestions() {
        return questions;
    }

    public void setQuestions(Question[] questions) {
        this.questions = questions;
    }

    public int getSize() {
        return questions.length == 0 ? 0 : questions.length-1;
    }
}
