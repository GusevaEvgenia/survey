package com.survey.mvc.dao;

import com.survey.mvc.entity.FormsEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
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
        FormsEntity formToUpdate = getForm(form.getIdForm());
        formToUpdate.setTitle(form.getTitle());
        formToUpdate.setDescription(form.getDescription());
        formToUpdate.setStartText(form.getStartText());
        formToUpdate.setFinishText(form.getFinishText());
//        formToUpdate.setPicture(form.getPicture());
        formToUpdate.setDateFinish(form.getDateFinish());
        formToUpdate.setMaximumForms(form.getMaximumForms());
        formToUpdate.setStatus(form.getStatus());
        getCurrentSession().update(formToUpdate);
    }

    @Override
    public FormsEntity getForm(int id) {
        FormsEntity form = (FormsEntity) getCurrentSession().get(FormsEntity.class, id);
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
}
