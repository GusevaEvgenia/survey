package com.survey.mvc.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Belkin on 10.11.2014.
 */
public class AnswersEntityPK implements Serializable {
    private int idCform;
    private int idOption;

    @Column(name = "id_cform")
    @Id
    public int getIdCform() {
        return idCform;
    }

    public void setIdCform(int idCform) {
        this.idCform = idCform;
    }

    @Column(name = "id_option")
    @Id
    public int getIdOption() {
        return idOption;
    }

    public void setIdOption(int idOption) {
        this.idOption = idOption;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnswersEntityPK that = (AnswersEntityPK) o;

        if (idCform != that.idCform) return false;
        if (idOption != that.idOption) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCform;
        result = 31 * result + idOption;
        return result;
    }
}
