package com.survey.mvc.controller.analysis;

import com.survey.mvc.controller.AbstractController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/analysis")
public class AnalysisController extends AbstractController {

    @RequestMapping(method = RequestMethod.GET)
    public String indexAction(ModelMap model) {
        return getView("index");
    }

    @Override
    protected String getViewPath() {
        return "analysis";
    }
}
