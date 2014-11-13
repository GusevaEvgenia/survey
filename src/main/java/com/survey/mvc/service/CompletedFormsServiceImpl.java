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
    public void updateCompletedForm(CompletedFormsEntity completedForm) {
        completedFormsDAO.updateCompletedForm(completedForm);
    }

    @Override
    public CompletedFormsEntity getCompletedForm(int id) {
        return completedFormsDAO.getCompletedForm(id);
    }

    @Override
    public List<CompletedFormsEntity> getCompletedForms() {
        return completedFormsDAO.getCompletedForms();
    }
}
