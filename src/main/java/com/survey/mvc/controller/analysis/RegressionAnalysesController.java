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
    private static final String SESSION_KEY = "regression_questions";
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

    //запись вопросов в сессию
    @RequestMapping(method = RequestMethod.POST, value = "/result")
    public String resultAction(ModelMap model, @PathVariable("id") int id, HttpServletRequest request) {
        int[] questions = {
                Integer.parseInt(request.getParameter("main_parameter")),
                Integer.parseInt(request.getParameter("first_parameter"))
        };
        putIntoSession(SESSION_KEY, questions);
        return "redirect:/forms/"+id+"/analysis/regression/result";
    }

    //переход на страницу результата, на первый таб
    @RequestMapping(method = RequestMethod.GET, value = "/result")
    public String getResultAction(ModelMap model, @PathVariable("id") int id) {
        model.addAttribute("form", formsService.getForm(id));
        int[] questions = getQuestions();
        if(questions == null) {
            return "redirect:/forms/"+id+"/analysis/regression";
        }
        Regression r = new Regression(analysisService.getAnalysisData(questions));
        model.addAttribute("regress", r);
        return getView("result");
    }

    //переход на следующий таб
    @RequestMapping(method = RequestMethod.GET, value = "/step/{step:[0-9]+}")
    public String getPageAction(ModelMap model, @PathVariable("step") String step, HttpServletRequest request) {
        int[] questions = getQuestions();
        Regression r = new Regression(analysisService.getAnalysisData(questions));
        switch (Integer.parseInt(step)){
            case 2: //со 1-ого на 2-ий
                //TODO преобразование в лин форму если нужно
                break;
            case 3: //со 2-ого на 3-ий
                r.setImportant_level(Double.parseDouble(request.getParameter("important_level")));
                break;
            case 5: //со 3-ого на 5-ий
                r.setImportant_level(Double.parseDouble(request.getParameter("important_level")));
                break;
        }

        model.addAttribute("regress", r);
        return getView("steps/"+step);
    }

    //последняя страница анализа
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
    protected int[] getQuestions() {
        return (int[]) getFromSession(SESSION_KEY);
    }
}
