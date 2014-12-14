package com.survey.mvc.service;

import com.survey.mvc.entity.FormsEntity;
import com.survey.mvc.model.designer.Designer;

import java.util.List;

/**
 * Created by Belkin on 12.11.2014.
 */
public interface FormsService {
    public void addForm(FormsEntity form);
    public void updateForm(FormsEntity form);
    public FormsEntity getForm(int id);
    public FormsEntity getLoadedForm(int id);
    public void deleteForm(int id);
    public List<FormsEntity> getForms();
    public List<FormsEntity> getFormsByStatus(String name);
    public Designer getDesignerByFormId(int id);
    public Designer getDesignerByForm(FormsEntity form);
    public FormsEntity designer(Designer designer, int formId);
    public FormsEntity getFormByLink(String hash);
    public void setActive(int id);
    public void setPicture(String name, int id);
}
