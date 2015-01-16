package com.survey.mvc.model.integration;

import com.survey.mvc.entity.UsersEntity;
import com.survey.mvc.model.CompletedFormRow;
import com.survey.mvc.model.integration.model.Form;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Belkin on 07.12.2014.
 */
public interface ThirdPartySurvey {

    public void setToken(String token);
    public Collection<Form> getForms();
    public Form getForm(int id);
    public ArrayList<CompletedFormRow> getAnswers(Form form);
    public String getAuthUrl(int user);
}
