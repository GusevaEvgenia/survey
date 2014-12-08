package com.survey.mvc.controller;

import com.google.gson.Gson;
import com.survey.mvc.entity.CompletedFormsEntity;
import com.survey.mvc.entity.FormsEntity;
import com.survey.mvc.model.designer.Designer;
import com.survey.mvc.service.CompletedFormsService;
import com.survey.mvc.service.FormsService;
import com.survey.mvc.service.QuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("forms/{id:[0-9]+}/designer")
public class DesignerController extends AbstractController{

    @Autowired
    private FormsService formsService;
    @Autowired
    private CompletedFormsService completedFormsService;

    //страница конструктора
    @RequestMapping(method = RequestMethod.GET)
    public String indexAction(ModelMap model, @PathVariable("id") Integer id) {
        model.addAttribute("newAnsEx", completedFormsService.newAnswersExist(id));
        model.addAttribute("form", formsService.getLoadedForm(id));
        model.addAttribute("flag", false);
        return getView("designer");
    }

    //сохранение изменений
    @RequestMapping(method = RequestMethod.POST)
    public String saveAction(ModelMap model, @ModelAttribute("form") Designer designer, @PathVariable("id") Integer id) {
        model.addAttribute("form", formsService.getForm(id));
        FormsEntity newForm = formsService.designer(designer, id);
        model.addAttribute("newForm", newForm);
        if(!formsService.getForm(id).getStatus().equals("draft")){
            model.addAttribute("flag", true);
        }
        return getView("designer");
    }

    //предпросмотр
    @RequestMapping(method = RequestMethod.POST, value = "/preview")
    public String previewAction(ModelMap model, @ModelAttribute("form") Designer designer, @PathVariable("id") Integer id) {
        model.addAttribute("form", formsService.getForm(id));
        model.addAttribute("designer", designer);
        return getView("form");
    }

    @Override
    protected String getViewPath() {
        return "forms/form";
    }
}
