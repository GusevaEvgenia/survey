package com.survey.mvc.controller.analysis;

import com.survey.mvc.controller.AbstractController;
import com.survey.mvc.model.analysis.Table;
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
@RequestMapping("/forms/{id}/analysis/table")
public class TableAnalysisController extends AbstractController {

    @Autowired
    private FormsService formsService;
    @Autowired
    private QuestionsService questionsService;
    @Autowired
    private AnalysisService analysisService;


    @RequestMapping(method = RequestMethod.GET)
     public String indexAction(ModelMap model, @PathVariable("id") Integer id) {
        model.addAttribute("form", formsService.getForm(id));
        model.addAttribute("questions", questionsService.getQuestionByForm(id));
        return getView("index");
    }


    @RequestMapping(method = RequestMethod.GET, value = "/result")
    public String resultAction(ModelMap model, @PathVariable("id") Integer id, HttpServletRequest request) {
        model.addAttribute("form", formsService.getForm(id));
        String [] types = request.getParameterValues("types");
        int[] questions = {Integer.parseInt(request.getParameter("main_parameter")),
                           Integer.parseInt(request.getParameter("first_parameter"))};
        Table t = new Table(analysisService.getAnalysisData(questions), types);
        model.addAttribute("table", t);
        return getView("result");
    }

    @Override
    protected String getViewPath() {
        return "analysis/table";
    }
}
