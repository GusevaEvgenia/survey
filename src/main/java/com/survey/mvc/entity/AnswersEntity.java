package com.survey.mvc.entity;

import javax.persistence.*;

/**
 * Created by Belkin on 10.11.2014.
 */
@Entity
@Table(name = "answers", schema = "", catalog = "research")
@IdClass(AnswersEntityPK.class)
public class AnswersEntity {
    private int idCform;
    private int idOption;
    private String text;
    private CompletedFormsEntity completedFormsByIdCform;
    private AnswerOptionsEntity answerOptionsByIdOption;

    @Id
    @Column(name = "id_cform")
    public int getIdCform() {
        return idCform;
    }

    public void setIdCform(int idCform) {
        this.idCform = idCform;
    }

    @Id
    @Column(name = "id_option")
    public int getIdOption() {
        return idOption;
    }

    public void setIdOption(int idOption) {
        this.idOption = idOption;
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

        AnswersEntity that = (AnswersEntity) o;

        if (idCform != that.idCform) return false;
        if (idOption != that.idOption) return false;
        if (text != null ? !text.equals(that.text) : that.text != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCform;
        result = 31 * result + idOption;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "id_cform", referencedColumnName = "id_cform", nullable = false)
    public CompletedFormsEntity getCompletedFormsByIdCform() {
        return completedFormsByIdCform;
    }

    public void setCompletedFormsByIdCform(CompletedFormsEntity completedFormsByIdCform) {
        this.completedFormsByIdCform = completedFormsByIdCform;
    }

    @ManyToOne
    @JoinColumn(name = "id_option", referencedColumnName = "id_option", nullable = false)
    public AnswerOptionsEntity getAnswerOptionsByIdOption() {
        return answerOptionsByIdOption;
    }

    public void setAnswerOptionsByIdOption(AnswerOptionsEntity answerOptionsByIdOption) {
        this.answerOptionsByIdOption = answerOptionsByIdOption;
    }
}
