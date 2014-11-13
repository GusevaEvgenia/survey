package com.survey.mvc.service;

import com.survey.mvc.entity.FormsEntity;

import java.util.List;

/**
 * Created by Belkin on 12.11.2014.
 */
public interface FormsService {
    public void addForm(FormsEntity form);
    public void updateForm(FormsEntity form);
    public FormsEntity getForm(int id);
    public void deleteForm(int id);
    public List<FormsEntity> getForms();
}
