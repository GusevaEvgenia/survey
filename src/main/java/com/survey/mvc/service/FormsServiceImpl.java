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
import java.util.Collection;
import java.util.List;
import java.util.Random;

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

    @Override
    public Designer getDesignerByFormId(int id) {
        return new Designer(getForm(id));
    }

    public Designer getDesignerByForm(FormsEntity form) {
        return new Designer(form);
    }

    @Override
    public void designer(Designer designer, int formId) {
        Question[] questions = designer.getQuestions();
        FormsEntity f = getForm(formId);
        ArrayList<QuestionsEntity> questionsEntities = new ArrayList<QuestionsEntity>();
        for(int i=0; i<questions.length; i++) {
            int orderQ = i + 1;
            QuestionsEntity q = new QuestionsEntity();
            q.setIdForm(formId);
            q.setIdQtype(questions[i].getIdType());
            q.setText(questions[i].getText());
            q.setOrder(orderQ);
            ArrayList<AnswerOptionsEntity> ansOp = new ArrayList<AnswerOptionsEntity>();
            Option[] options = questions[i].getOptions();
            for(int j = 0; j < options.length; j++ ) {
                int orderO = j + 1;
                AnswerOptionsEntity a = new AnswerOptionsEntity();
                //a.setIdQuestion(); //код вопроса
                a.setText(options[j].getText());
                a.setTextMatrix(options[j].getTextMatrix());
                a.setOrder(orderO);
                ansOp.add(a);
            }
            q.setAnswerOptionsesByIdQuestion(ansOp);
            questionsEntities.add(q);
        }
        f.setQuestionsesByIdForm(questionsEntities);
        updateForm(f);
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
}
