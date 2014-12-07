package com.survey.mvc.service;

import com.survey.mvc.dao.CompletedFormsDAO;
import com.survey.mvc.entity.CompletedFormsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CompletedFormsServiceImpl implements CompletedFormsService {
    @Autowired
    private CompletedFormsDAO completedFormsDAO;

    @Override
    public void addCompletedForm(CompletedFormsEntity completedForm) {
        completedFormsDAO.addCompletedForm(completedForm);
    }

    @Override
    public void updateStatus(int id, String status) {
        completedFormsDAO.updateStatus(id, status);
    }

    @Override
    public CompletedFormsEntity getCompletedForm(int id) {
        return completedFormsDAO.getCompletedForm(id);
    }

    @Override
    public List<CompletedFormsEntity> getCompletedForms() {
        return completedFormsDAO.getCompletedForms();
    }

    @SuppressWarnings("unchecked")
    public List<CompletedFormsEntity> getCompletedFormsByForm(int id){
        return completedFormsDAO.getCompletedFormsByForm(id);
    }

    @Override
    public boolean newAnswersExist(int id) {
        return completedFormsDAO.getCompletedFormsByForm(id, "new").size() > 0;
    }
}
