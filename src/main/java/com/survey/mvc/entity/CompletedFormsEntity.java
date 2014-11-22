package com.survey.mvc.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Belkin on 10.11.2014.
 */
@Entity
@Table(name = "completed_forms", schema = "", catalog = "research")
public class CompletedFormsEntity {
    private int idCform;
    private int idForm;
    private Timestamp date;
    private String status;
    private Collection<AnswersEntity> answersesByIdCform;
    private FormsEntity formsByIdForm;

    @Id
    @Column(name = "id_cform")
    public int getIdCform() {
        return idCform;
    }

    public void setIdCform(int idCform) {
        this.idCform = idCform;
    }

    @Basic
    @Column(name = "id_form")
    public int getIdForm() {
        return idForm;
    }

    public void setIdForm(int idForm) {
        this.idForm = idForm;
    }

    @Basic
    @Column(name = "date")
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Basic
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompletedFormsEntity that = (CompletedFormsEntity) o;

        if (idCform != that.idCform) return false;
        if (idForm != that.idForm) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCform;
        result = 31 * result + idForm;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "completedFormsByIdCform")
    public Collection<AnswersEntity> getAnswersesByIdCform() {
        return answersesByIdCform;
    }

    public void setAnswersesByIdCform(Collection<AnswersEntity> answersesByIdCform) {
        this.answersesByIdCform = answersesByIdCform;
    }

    @ManyToOne
    @JoinColumn(name = "id_form", referencedColumnName = "id_form", nullable = false, insertable = false, updatable = false)
    public FormsEntity getFormsByIdForm() {
        return formsByIdForm;
    }

    public void setFormsByIdForm(FormsEntity formsByIdForm) {
        this.formsByIdForm = formsByIdForm;
    }
}
