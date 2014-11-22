package com.survey.mvc.service;

import com.survey.mvc.dao.FormsDAO;
import com.survey.mvc.entity.FormsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FormsServiceImpl implements FormsService {

    @Autowired
    private FormsDAO formsDAO;

    @Override
    public void addForm(FormsEntity form) {
        formsDAO.addForm(form);
    }

    @Override
    public void updateForm(FormsEntity form) {
        formsDAO.updateForm(form);
    }

    @Override
    public FormsEntity getForm(int id) {
        return formsDAO.getForm(id);
    }

    @Override
    public FormsEntity getLoadedForm(int id) {
        return formsDAO.getForm(id, true);
    }

    @Override
    public void deleteForm(int id) {
        formsDAO.deleteForm(id);
    }

    @Override
    public List<FormsEntity> getForms() {
        return formsDAO.getForms();
    }

    @Override
    public List<FormsEntity> getFormsByStatus(String name) {
        return formsDAO.getFormsByStatus(name);
    }
}
