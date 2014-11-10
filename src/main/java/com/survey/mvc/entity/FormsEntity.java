package com.survey.mvc.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by Belkin on 10.11.2014.
 */
@Entity
@Table(name = "forms", schema = "", catalog = "research")
public class FormsEntity {
    private int idForm;
    private int idUser;
    private String title;
    private String description;
    private String startText;
    private String finishText;
    private String picture;
    private Timestamp dateStart;
    private Timestamp dateFinish;
    private Integer maximumForms;
    private Byte draft;
    private String link;
    private String status;
    private Collection<CompletedFormsEntity> completedFormsesByIdForm;
    private UsersEntity usersByIdUser;
    private Collection<QuestionsEntity> questionsesByIdForm;

    @Id
    @Column(name = "id_form")
    public int getIdForm() {
        return idForm;
    }

    public void setIdForm(int idForm) {
        this.idForm = idForm;
    }

    @Basic
    @Column(name = "id_user")
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "start_text")
    public String getStartText() {
        return startText;
    }

    public void setStartText(String startText) {
        this.startText = startText;
    }

    @Basic
    @Column(name = "finish_text")
    public String getFinishText() {
        return finishText;
    }

    public void setFinishText(String finishText) {
        this.finishText = finishText;
    }

    @Basic
    @Column(name = "picture")
    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Basic
    @Column(name = "date_start")
    public Timestamp getDateStart() {
        return dateStart;
    }

    public void setDateStart(Timestamp dateStart) {
        this.dateStart = dateStart;
    }

    @Basic
    @Column(name = "date_finish")
    public Timestamp getDateFinish() {
        return dateFinish;
    }

    public void setDateFinish(Timestamp dateFinish) {
        this.dateFinish = dateFinish;
    }

    @Basic
    @Column(name = "maximum_forms")
    public Integer getMaximumForms() {
        return maximumForms;
    }

    public void setMaximumForms(Integer maximumForms) {
        this.maximumForms = maximumForms;
    }

    @Basic
    @Column(name = "draft")
    public Byte getDraft() {
        return draft;
    }

    public void setDraft(Byte draft) {
        this.draft = draft;
    }

    @Basic
    @Column(name = "link")
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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

        FormsEntity that = (FormsEntity) o;

        if (idForm != that.idForm) return false;
        if (idUser != that.idUser) return false;
        if (dateFinish != null ? !dateFinish.equals(that.dateFinish) : that.dateFinish != null) return false;
        if (dateStart != null ? !dateStart.equals(that.dateStart) : that.dateStart != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (draft != null ? !draft.equals(that.draft) : that.draft != null) return false;
        if (finishText != null ? !finishText.equals(that.finishText) : that.finishText != null) return false;
        if (link != null ? !link.equals(that.link) : that.link != null) return false;
        if (maximumForms != null ? !maximumForms.equals(that.maximumForms) : that.maximumForms != null) return false;
        if (picture != null ? !picture.equals(that.picture) : that.picture != null) return false;
        if (startText != null ? !startText.equals(that.startText) : that.startText != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idForm;
        result = 31 * result + idUser;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (startText != null ? startText.hashCode() : 0);
        result = 31 * result + (finishText != null ? finishText.hashCode() : 0);
        result = 31 * result + (picture != null ? picture.hashCode() : 0);
        result = 31 * result + (dateStart != null ? dateStart.hashCode() : 0);
        result = 31 * result + (dateFinish != null ? dateFinish.hashCode() : 0);
        result = 31 * result + (maximumForms != null ? maximumForms.hashCode() : 0);
        result = 31 * result + (draft != null ? draft.hashCode() : 0);
        result = 31 * result + (link != null ? link.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "formsByIdForm")
    public Collection<CompletedFormsEntity> getCompletedFormsesByIdForm() {
        return completedFormsesByIdForm;
    }

    public void setCompletedFormsesByIdForm(Collection<CompletedFormsEntity> completedFormsesByIdForm) {
        this.completedFormsesByIdForm = completedFormsesByIdForm;
    }

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id_user", nullable = false, insertable = false, updatable = false)
    public UsersEntity getUsersByIdUser() {
        return usersByIdUser;
    }

    public void setUsersByIdUser(UsersEntity usersByIdUser) {
        this.usersByIdUser = usersByIdUser;
    }

    @OneToMany(mappedBy = "formsByIdForm")
    public Collection<QuestionsEntity> getQuestionsesByIdForm() {
        return questionsesByIdForm;
    }

    public void setQuestionsesByIdForm(Collection<QuestionsEntity> questionsesByIdForm) {
        this.questionsesByIdForm = questionsesByIdForm;
    }
}
