package com.survey.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/forms/{id}/analysis/regression")
public class RegressionAnalysesController extends AbstractController{

    @RequestMapping(method = RequestMethod.GET)
    public String indexAction(ModelMap model, @PathVariable("id") String id) {
        return getView("index");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/result")
    public String resultAction(ModelMap model, @PathVariable("id") String id) {
        return getView("result");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/prognoz")
    public String prognozAction(ModelMap model, @PathVariable("id") String id) {
        return getView("prognoz");
    }

    @Override
    String getViewPath() {
        return "analysis/regression";
    }
}
