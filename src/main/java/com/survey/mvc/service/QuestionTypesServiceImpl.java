package com.survey.mvc.service;

import com.survey.mvc.dao.QuestionTypesDAO;
import com.survey.mvc.entity.QuestionTypesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class QuestionTypesServiceImpl implements QuestionTypesService {

    @Autowired
    private QuestionTypesDAO typeDAO;

    @Override
    public QuestionTypesEntity getQuestionType(int id) {
        return typeDAO.getQuestionType(id);
    }

    @Override
    public List<QuestionTypesEntity> getQuestionTypes() {
        return typeDAO.getQuestionTypes();
    }
}
