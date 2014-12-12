package com.survey.mvc.service;

import com.survey.mvc.dao.CompletedFormsDAO;
import com.survey.mvc.entity.AnswersEntity;
import com.survey.mvc.entity.CompletedFormsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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
    public void save(int id, ArrayList<Integer> options){
        CompletedFormsEntity cform = new CompletedFormsEntity();
        cform.setIdForm(id);
        cform.setDate(new Timestamp((new Date()).getTime()));
        cform.setStatus("new");
        completedFormsDAO.addCompletedForm(cform);

        int idCform = cform.getIdCform();

        ArrayList<AnswersEntity> answers = new ArrayList<AnswersEntity>();
        for(int idOption : options) {
            AnswersEntity answer = new AnswersEntity();
            answer.setCompletedFormsByIdCform(cform);
            answer.setIdCform(idCform);
            answer.setIdOption(idOption);
            answers.add(answer);
        }

        cform.setAnswersesByIdCform(answers);
        completedFormsDAO.saveCompletedForm(cform);


    }

    @Override
    public boolean newAnswersExist(int id) {
        return completedFormsDAO.getCompletedFormsByForm(id, "new").size() > 0;
    }
}
