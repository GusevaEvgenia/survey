package com.survey.mvc.controller;

import com.google.gson.Gson;
import com.survey.mvc.entity.FormsEntity;
import com.survey.mvc.model.designer.Designer;
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
    private QuestionsService questionsService;

    @RequestMapping(method = RequestMethod.GET)
    public String indexAction(ModelMap model, @PathVariable("id") Integer id) {
        model.addAttribute("form", formsService.getLoadedForm(id));
        return getView("designer");
    }

    @RequestMapping(method = RequestMethod.POST)
    public String saveAction(ModelMap model, @ModelAttribute("form") Designer designer, @PathVariable("id") Integer id) {
        model.addAttribute("form", formsService.getForm(id));
        formsService.designer(designer, id);
        return getView("designer");
    }

    @RequestMapping(method = RequestMethod.POST, value = "/preview")
    public String previewAction(ModelMap model, @ModelAttribute("form") Designer designer, @PathVariable("id") Integer id) {
        model.addAttribute("designer", designer);
        return getView("form");
    }

    @Override
    protected String getViewPath() {
        return "forms/form";
    }
}
