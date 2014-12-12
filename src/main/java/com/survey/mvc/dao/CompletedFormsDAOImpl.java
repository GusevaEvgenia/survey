package com.survey.mvc.dao;

import com.survey.mvc.entity.CompletedFormsEntity;
import com.survey.mvc.entity.FormsEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CompletedFormsDAOImpl implements CompletedFormsDAO {
    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void addCompletedForm(CompletedFormsEntity completedForm) {
        saveOrUpdateCompletedForm(completedForm);
        getCurrentSession().refresh(completedForm);
    }

    @Override
    public void saveOrUpdateCompletedForm(CompletedFormsEntity completedForm) {
        getCurrentSession().saveOrUpdate(completedForm);
        getCurrentSession().flush();
    }

    @Override
    public void saveCompletedForm(CompletedFormsEntity completedForm) {
        getCurrentSession().save(completedForm);
    }

    @Override
    public void updateStatus(int id, String status) {
        CompletedFormsEntity completedFormToUpdate = getCompletedForm(id);
        completedFormToUpdate.setStatus(status);
        getCurrentSession().update(completedFormToUpdate);
    }

    @Override
    public CompletedFormsEntity getCompletedForm(int id) {
        CompletedFormsEntity completedForm = (CompletedFormsEntity) getCurrentSession().get(CompletedFormsEntity.class, id);
        return completedForm;
    }

    @SuppressWarnings("unchecked")
    public List<CompletedFormsEntity> getCompletedForms() {
        return getCurrentSession().createQuery("from CompletedFormsEntity").list();
    }

    @SuppressWarnings("unchecked")
    public List<CompletedFormsEntity> getCompletedFormsByForm(int id) {
        return getCurrentSession().createQuery("from CompletedFormsEntity comp where comp.idForm = :id")
                .setInteger("id", id).list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<CompletedFormsEntity> getCompletedFormsByForm(int id, String status) {
        return getCurrentSession().createQuery("from CompletedFormsEntity comp where comp.idForm = :id and status = :status")
                .setInteger("id", id).setString("status", status).list();
    }
}
