package com.survey.mvc.controller.analysis;

import com.survey.mvc.controller.AbstractController;
import com.survey.mvc.model.analysis.Regression;
import com.survey.mvc.service.AnalysisService;
import com.survey.mvc.service.FormsService;
import com.survey.mvc.service.QuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/forms/{id}/analysis/regression")
public class RegressionAnalysesController extends AbstractController {

    @Autowired
    private FormsService formsService;
    @Autowired
    private QuestionsService questionsService;
    @Autowired
    private AnalysisService analysisService;

    @RequestMapping(method = RequestMethod.GET)
    public String indexAction(ModelMap model, @PathVariable("id") int id) {
        model.addAttribute("form", formsService.getForm(id));
        model.addAttribute("questions", questionsService.getQuestionByForm(id));
        return getView("index");
    }

    @RequestMapping(method = RequestMethod.POST, value = "/result")
    public String resultAction(ModelMap model, @PathVariable("id") int id, HttpServletRequest request) {
        model.addAttribute("form", formsService.getForm(id));
        int[] questions = {Integer.parseInt(request.getParameter("main_parameter")),
                           Integer.parseInt(request.getParameter("first_parameter"))};
        Regression r = new Regression(analysisService.getAnalysisData(questions));
        model.addAttribute("regress", r);
        return getView("result");
    }

    @RequestMapping(method = RequestMethod.POST, value = "/prognoz")
    public String prognozAction(ModelMap model, @PathVariable("id") int id,  HttpServletRequest request) {
        model.addAttribute("form", formsService.getForm(id));
        double a = Double.parseDouble(request.getParameter("paramA"));
        double b = Double.parseDouble(request.getParameter("paramB"));
        /*Regression r = new Regression(a, b);
        model.addAttribute("prognoz", r);*/
        return getView("prognoz");
    }

    @Override
    protected String getViewPath() {
        return "analysis/regression";
    }
}
