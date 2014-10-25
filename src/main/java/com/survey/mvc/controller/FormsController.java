package com.survey.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/forms")
public class FormsController extends AbstractController {

    @RequestMapping(method = RequestMethod.GET)
    public String indexAction(ModelMap model) {
        //model.addAttribute("message", "Hello world!");
        return getView("index");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id:[0-9]+}")
      public String showAction(ModelMap model, @PathVariable("id") Integer id) {
        model.addAttribute("formId", id);
        return getView("show");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/new")
    public String newAction(ModelMap model) {
        return getView("new");
    }

    @RequestMapping(method = RequestMethod.POST)
    public String createAction(ModelMap model) {
        return "redirect:/forms/123";
    }

    @Override
    protected String getViewPath() {
        return "forms";
    }
}
