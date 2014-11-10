package com.survey.mvc.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Belkin on 10.11.2014.
 */
@Entity
@Table(name = "answer_options", schema = "", catalog = "research")
public class AnswerOptionsEntity {
    private int idOption;
    private int idQuestion;
    private String text;
    private QuestionsEntity questionsByIdQuestion;
    private Collection<AnswersEntity> answersesByIdOption;

    @Id
    @Column(name = "id_option")
    public int getIdOption() {
        return idOption;
    }

    public void setIdOption(int idOption) {
        this.idOption = idOption;
    }

    @Basic
    @Column(name = "id_question")
    public int getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(int idQuestion) {
        this.idQuestion = idQuestion;
    }

    @Basic
    @Column(name = "text")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnswerOptionsEntity that = (AnswerOptionsEntity) o;

        if (idOption != that.idOption) return false;
        if (idQuestion != that.idQuestion) return false;
        if (text != null ? !text.equals(that.text) : that.text != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idOption;
        result = 31 * result + idQuestion;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "id_question", referencedColumnName = "id_question", nullable = false, insertable = false, updatable = false)
    public QuestionsEntity getQuestionsByIdQuestion() {
        return questionsByIdQuestion;
    }

    public void setQuestionsByIdQuestion(QuestionsEntity questionsByIdQuestion) {
        this.questionsByIdQuestion = questionsByIdQuestion;
    }

    @OneToMany(mappedBy = "answerOptionsByIdOption")
    public Collection<AnswersEntity> getAnswersesByIdOption() {
        return answersesByIdOption;
    }

    public void setAnswersesByIdOption(Collection<AnswersEntity> answersesByIdOption) {
        this.answersesByIdOption = answersesByIdOption;
    }
}
