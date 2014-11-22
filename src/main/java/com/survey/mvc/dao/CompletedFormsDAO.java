package com.survey.mvc.dao;

import com.survey.mvc.entity.CompletedFormsEntity;

import java.util.List;

/**
 * Created by Belkin on 12.11.2014.
 */
public interface CompletedFormsDAO {
    public void addCompletedForm(CompletedFormsEntity completedForm);
    public void updateStatus(int id, String  status);
    public CompletedFormsEntity getCompletedForm(int id);
    public List<CompletedFormsEntity> getCompletedForms();
    public List<CompletedFormsEntity> getCompletedFormsByForm(int id);
}
