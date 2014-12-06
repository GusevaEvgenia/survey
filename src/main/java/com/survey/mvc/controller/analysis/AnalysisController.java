package com.survey.mvc.controller.analysis;

import com.survey.mvc.controller.AbstractController;
import com.survey.mvc.service.FormsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/analysis")
public class AnalysisController extends AbstractController {

    @Autowired
    private FormsService formsService;

    @RequestMapping(method = RequestMethod.GET)
    public String indexAction(ModelMap model) {
        model.addAttribute("forms", formsService.getForms());
        return getView("index");
    }

    @Override
    protected String getViewPath() {
        return "analysis";
    }
}
