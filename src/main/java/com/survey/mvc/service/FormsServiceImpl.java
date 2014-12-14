package com.survey.mvc.service;

import com.survey.mvc.dao.FormsDAO;
import com.survey.mvc.entity.AnswerOptionsEntity;
import com.survey.mvc.entity.FormsEntity;
import com.survey.mvc.entity.QuestionsEntity;
import com.survey.mvc.model.designer.Designer;
import com.survey.mvc.model.designer.Option;
import com.survey.mvc.model.designer.Question;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class FormsServiceImpl implements FormsService {

    @Autowired
    private FormsDAO formsDAO;

    @Override
    public void addForm(FormsEntity form) {
        formsDAO.saveForm(form);
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

    @Override
    public Designer getDesignerByFormId(int id) {
        return new Designer(getForm(id));
    }

    public Designer getDesignerByForm(FormsEntity form) {
        return new Designer(form);
    }

    @Override
    public FormsEntity designer(Designer designer, int formId) {
        Question[] questions = designer.getQuestions();
        FormsEntity f = getForm(formId);
        if(!f.getStatus().equals("draft")){
            f = formsDAO.clone(f);
            formsDAO.saveOrUpdateForm(f, true);
            formId = f.getIdForm();
        }

        ArrayList<QuestionsEntity> questionsEntities = new ArrayList<QuestionsEntity>();
        for(int i=0; i<questions.length; i++) {
            int orderQ = i + 1;
            QuestionsEntity q = new QuestionsEntity();
            q.setIdForm(formId);
            q.setFormsByIdForm(f);
            q.setIdQtype(questions[i].getIdType());
            q.setText(questions[i].getText());
            q.setOrder(orderQ);
            q.setScale(questions[i].getScale());

            questionsEntities.add(q);
        }
        f.setQuestionsesByIdForm(questionsEntities);
        formsDAO.saveOrUpdateForm(f);

        for(QuestionsEntity qe : f.getQuestionsesByIdForm()) {
            ArrayList<AnswerOptionsEntity> ansOp = new ArrayList<AnswerOptionsEntity>();
            Option[] options = questions[qe.getOrder()-1].getOptions();
            for(int j = 0; j < options.length; j++ ) {
                int orderO = j + 1;
                AnswerOptionsEntity a = new AnswerOptionsEntity();
                a.setIdQuestion(qe.getIdQuestion());
                a.setQuestionsByIdQuestion(qe);
                a.setText(options[j].getText());
                a.setTextMatrix(options[j].getTextMatrix());
                a.setOrder(orderO);
                ansOp.add(a);
            }
            qe.setAnswerOptionsesByIdQuestion(ansOp);
        }
        formsDAO.saveForm(f);
        return f;
    }

    @Override
    public FormsEntity getFormByLink(String hash) {
        List<FormsEntity> forms = formsDAO.getFormByLink(hash);
        if(forms.size() != 1) {
            return null;
        }
        FormsEntity form= forms.get(0);
        Hibernate.initialize(form.getQuestionsesByIdForm());
        return form;
    }

    @Override
    public void setActive(int id){
        formsDAO.getForm(id).setStatus("active");
        setPicture("/uploads/active.jpg", id);
    }

    @Override
    public void setArchive(int id){
        formsDAO.getForm(id).setStatus("archive");
        setPicture("/uploads/archive.jpg", id);
        deleteLink(id);
    }

    @Override
    public void setPicture(String name, int id) {
        formsDAO.getForm(id).setPicture(name);
    }

    @Override
    public void deleteLink(int id) {
        formsDAO.getForm(id).setLink(null);
    }
}
