package com.survey.mvc.dao;

import com.survey.mvc.entity.FormsEntity;

import java.util.List;

/**
 * Created by Belkin on 12.11.2014.
 */
public interface FormsDAO {
    public void saveForm(FormsEntity form);
    public void updateForm(FormsEntity form);
    public void saveOrUpdateForm(FormsEntity form);
    public void saveOrUpdateForm(FormsEntity form, boolean refresh);
    public FormsEntity clone(FormsEntity form);
    public FormsEntity getForm(int id);
    public FormsEntity getForm(int id, boolean loaded);
    public void deleteForm(int id);
    public List<FormsEntity> getForms();
    public List<FormsEntity> getFormsByStatus(String name);
    public List<FormsEntity> getFormByLink(String hash);
}
