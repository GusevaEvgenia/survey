package com.survey.mvc.entity;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Belkin on 10.11.2014.
 */
@Entity
@Table(name = "questions", schema = "", catalog = "research")
public class QuestionsEntity {
    private int idQuestion;
    private int idForm;
    private int idQtype;
    private String text;
    private String scale;
    private int order;
    private Collection<AnswerOptionsEntity> answerOptionsesByIdQuestion;
    private FormsEntity formsByIdForm;
    private QuestionTypesEntity questionTypesByIdQtype;

    @Id
    @Column(name = "id_question")
    public int getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(int idQuestion) {
        this.idQuestion = idQuestion;
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
    @Column(name = "id_qtype")
    public int getIdQtype() {
        return idQtype;
    }

    public void setIdQtype(int idQtype) {
        this.idQtype = idQtype;
    }

    @Basic
    @Column(name = "text")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Basic
    @Column(name = "scale")
    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    @Basic
    @Column(name = "order")
    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuestionsEntity that = (QuestionsEntity) o;

        if (idForm != that.idForm) return false;
        if (idQtype != that.idQtype) return false;
        if (idQuestion != that.idQuestion) return false;
        if (scale != null ? !scale.equals(that.scale) : that.scale != null) return false;
        if (text != null ? !text.equals(that.text) : that.text != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idQuestion;
        result = 31 * result + idForm;
        result = 31 * result + idQtype;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (scale != null ? scale.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "questionsByIdQuestion", fetch = FetchType.EAGER)
    public Collection<AnswerOptionsEntity> getAnswerOptionsesByIdQuestion() {
        return answerOptionsesByIdQuestion;
    }

    public void setAnswerOptionsesByIdQuestion(Collection<AnswerOptionsEntity> answerOptionsesByIdQuestion) {
        this.answerOptionsesByIdQuestion = answerOptionsesByIdQuestion;
    }

    @ManyToOne
    @JoinColumn(name = "id_form", referencedColumnName = "id_form", nullable = false, insertable = false, updatable = false)
    public FormsEntity getFormsByIdForm() {
        return formsByIdForm;
    }

    public void setFormsByIdForm(FormsEntity formsByIdForm) {
        this.formsByIdForm = formsByIdForm;
    }

    @ManyToOne
    @JoinColumn(name = "id_qtype", referencedColumnName = "id_qtype", nullable = false, insertable = false, updatable = false)
    public QuestionTypesEntity getQuestionTypesByIdQtype() {
        return questionTypesByIdQtype;
    }

    public void setQuestionTypesByIdQtype(QuestionTypesEntity questionTypesByIdQtype) {
        this.questionTypesByIdQtype = questionTypesByIdQtype;
    }

    @Transient
    public String getTemplateName(){
        String name = "";
        switch(idQtype){
            case 1 :
                name = "single-option";
                break;
            case 2 :
                name = "multiple-option";
                break;
            case 3 :
                name = "number-option";
                break;
            case 4 :
                name = "select-option";
                break;
            case 5 :
                name = "matrix-single-option";
                break;
            case 6 :
                name = "matrix-multiple-option";
                break;
        }
        return name;
    }

    @Transient
    public JsonObject getJson() {
        JsonObject result = new JsonObject();
        result.addProperty("idForm", getIdForm());
        result.addProperty("idQuestion", getIdQuestion());
        result.addProperty("idQtype", getIdQtype());
        result.addProperty("text", getText());
        result.addProperty("scale", getScale());
        result.addProperty("order", getOrder());
        result.addProperty("templateName", getTemplateName());
        if (Hibernate.isInitialized(answerOptionsesByIdQuestion)) {
            JsonArray options = new JsonArray();
            for(AnswerOptionsEntity ao: getAnswerOptionsesByIdQuestion()) {
                options.add(ao.getJson());
            }
            result.add("options", options);
        }
        return result;
    }
}
