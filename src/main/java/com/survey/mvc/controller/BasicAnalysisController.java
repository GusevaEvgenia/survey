package com.survey.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/forms/{id}/analysis/basic")
public class BasicAnalysisController extends AbstractController {

    @RequestMapping(method = RequestMethod.GET)
    public String indexAction(ModelMap model, @PathVariable("id") String id) {
        return getView("index");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/methods")
    public String MethodsAction(ModelMap model, @PathVariable("id") String id) {
        return getView("methods");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/result")
    public String ResultAction(ModelMap model, @PathVariable("id") String id) {
        return getView("result");
    }

    @Override
    String getViewPath() {
        return "analysis/basic";
    }
}
