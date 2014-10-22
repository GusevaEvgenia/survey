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
        //model.addAttribute("message", "Hello world!");
        return getView("index");
    }

    @Override
    String getViewPath() {
        return "index";
    }
}
