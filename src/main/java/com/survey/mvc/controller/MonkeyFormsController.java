package com.survey.mvc.controller;

import com.survey.mvc.entity.UsersEntity;
import com.survey.mvc.model.integration.ThirdPartySurvey;
import com.survey.mvc.model.integration.model.Form;
import com.survey.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/monkey-forms")
public class MonkeyFormsController extends AbstractController{

    @Autowired
    private UserService userService;
    @Autowired
    private ThirdPartySurvey surveyMonkey;

    @RequestMapping(method = RequestMethod.GET)
    public String indexAction(ModelMap model) {
        surveyMonkey.setToken(getCurrentUser().getToken());
        model.addAttribute("forms", surveyMonkey.getForms());
        return getView("index");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id:[0-9]+}")
    public String showAction(ModelMap model, @PathVariable("id") Integer id) {
        surveyMonkey.setToken(getCurrentUser().getToken());
        model.addAttribute("form", surveyMonkey.getForm(id));
        return getView("show");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id:[0-9]+}/answers")
    public String answersAction(ModelMap model, @PathVariable("id") Integer id) {
        surveyMonkey.setToken(getCurrentUser().getToken());
        Form form = surveyMonkey.getForm(id);
        model.addAttribute("form", form);
        model.addAttribute("answers", surveyMonkey.getAnswers(form));
        return getView("answers");
    }

    @Override
    protected String getViewPath() {
        return "monkeyForms";
    }
}
