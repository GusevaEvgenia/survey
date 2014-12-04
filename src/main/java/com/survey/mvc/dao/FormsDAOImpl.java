package com.survey.mvc.dao;

import com.survey.mvc.entity.FormsEntity;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class FormsDAOImpl implements FormsDAO {
    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void addForm(FormsEntity form) {
        getCurrentSession().save(form);
    }

    @Override
    public void updateForm(FormsEntity form) {
        if(!getCurrentSession().contains(form)) {
            form = prepareEntity(form);
        }
        getCurrentSession().update(form);
    }

    protected FormsEntity prepareEntity(FormsEntity form) {
        FormsEntity formToUpdate = getForm(form.getIdForm());
        formToUpdate.setTitle(form.getTitle());
        formToUpdate.setDescription(form.getDescription());
        formToUpdate.setStartText(form.getStartText());
        formToUpdate.setFinishText(form.getFinishText());
//        formToUpdate.setPicture(form.getPicture());
        formToUpdate.setDateFinish(form.getDateFinish());
        formToUpdate.setMaximumForms(form.getMaximumForms());
        formToUpdate.setStatus(form.getStatus());
        return formToUpdate;
    }

    @Override
    public FormsEntity getForm(int id) {
        FormsEntity form = (FormsEntity) getCurrentSession().get(FormsEntity.class, id);
        return form;
    }

    @Override
    public FormsEntity getForm(int id, boolean loaded) {
        FormsEntity form = (FormsEntity) getCurrentSession().get(FormsEntity.class, id);
        if(loaded) {
            Hibernate.initialize(form.getQuestionsesByIdForm());
        }
        return form;
    }

    @Override
    public void deleteForm(int id) {
        getCurrentSession().delete(getForm(id));
    }

    @SuppressWarnings("unchecked")
    public List<FormsEntity> getForms() {
        return getCurrentSession().createQuery("from FormsEntity").list();
    }

    @SuppressWarnings("unchecked")
    public List<FormsEntity> getFormsByStatus(String statusName) {
        return getCurrentSession().createQuery("from FormsEntity forms where forms.status like :name")
                .setString("name", statusName).list();
    }

    @SuppressWarnings("unchecked")
    public List<FormsEntity> getFormByLink(String hash) {
        return getCurrentSession().createQuery("from FormsEntity forms where forms.link like :hash")
                .setString("hash", hash).list();
    }
}
