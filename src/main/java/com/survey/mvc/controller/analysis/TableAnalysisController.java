package com.survey.mvc.controller.analysis;

import com.survey.mvc.controller.AbstractController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/forms/{id}/analysis/table")
public class TableAnalysisController extends AbstractController {

    @RequestMapping(method = RequestMethod.GET)
     public String indexAction(ModelMap model) {
        return getView("index");
    }


    @RequestMapping(method = RequestMethod.GET, value = "/result")
    public String resultAction(ModelMap model) {
        return getView("result");
    }

    @Override
    protected String getViewPath() {
        return "analysis/table";
    }
}
