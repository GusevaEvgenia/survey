package com.survey.mvc.service;

import com.survey.mvc.entity.CompletedFormsEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Belkin on 12.11.2014.
 */
public interface CompletedFormsService {
    public void addCompletedForm(CompletedFormsEntity completedForm);
    public void updateStatus(int id, String status);
    public CompletedFormsEntity getCompletedForm(int id);
    public List<CompletedFormsEntity> getCompletedForms();
    public List<CompletedFormsEntity> getCompletedFormsByForm(int id);
    public void save(int id, ArrayList<Integer> options);
    public boolean newAnswersExist(int id);
}
