package com.survey.mvc.controller;

import com.survey.mvc.service.FormsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("forms/{id:[0-9]+}/designer")
public class DesignerController extends AbstractController{

    @Autowired
    private FormsService formsService;

    @RequestMapping(method = RequestMethod.GET)
    public String indexAction(ModelMap model, @PathVariable("id") Integer id) {
        model.addAttribute("form", formsService.getForm(id));
        return getView("designer");
    }

    @Override
    protected String getViewPath() {
        return "forms/form";
    }
}
