package com.survey.mvc.service;

import com.survey.mvc.entity.CompletedFormsEntity;

import java.util.List;

/**
 * Created by Belkin on 12.11.2014.
 */
public interface CompletedFormsService {
    public void addCompletedForm(CompletedFormsEntity completedForm);
    public void updateCompletedForm(CompletedFormsEntity completedForm);
    public CompletedFormsEntity getCompletedForm(int id);
    public List<CompletedFormsEntity> getCompletedForms();
}
