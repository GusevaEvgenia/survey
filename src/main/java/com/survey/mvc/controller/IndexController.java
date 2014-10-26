package com.survey.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class IndexController extends AbstractController{

    @RequestMapping(method = RequestMethod.GET)
    public String indexAction(ModelMap model) {
        model.addAttribute("login", "true");
        return getView("index");
    }

    @RequestMapping(method = RequestMethod.POST)
    public String registerAction(ModelMap model) {
        //model.addAttribute("login", "true");
        return getView("index");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user")
    public String userAction(ModelMap model) {
        model.addAttribute("conectSM", "true");
        return getView("user");
    }


    @Override
    protected String getViewPath() {
        return "index";
    }
}
