package com.survey.mvc.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Belkin on 10.11.2014.
 */
@Entity
@Table(name = "question_types", schema = "", catalog = "research")
public class QuestionTypesEntity {
    private int idQtype;
    private String name;
    private Collection<QuestionsEntity> questionsesByIdQtype;

    @Id
    @Column(name = "id_qtype")
    public int getIdQtype() {
        return idQtype;
    }

    public void setIdQtype(int idQtype) {
        this.idQtype = idQtype;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuestionTypesEntity that = (QuestionTypesEntity) o;

        if (idQtype != that.idQtype) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idQtype;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "questionTypesByIdQtype")
    public Collection<QuestionsEntity> getQuestionsesByIdQtype() {
        return questionsesByIdQtype;
    }

    public void setQuestionsesByIdQtype(Collection<QuestionsEntity> questionsesByIdQtype) {
        this.questionsesByIdQtype = questionsesByIdQtype;
    }
}
