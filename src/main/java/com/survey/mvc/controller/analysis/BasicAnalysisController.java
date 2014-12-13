package com.survey.mvc.controller.analysis;

import com.survey.mvc.controller.AbstractController;
import com.survey.mvc.entity.QuestionsEntity;
import com.survey.mvc.model.analysis.Basic;
import com.survey.mvc.model.analysis.data.AnalysisData;
import com.survey.mvc.service.AnalysisService;
import com.survey.mvc.service.FormsService;
import com.survey.mvc.service.QuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/forms/{id}/analysis/basic")
public class BasicAnalysisController extends AbstractController {

    @Autowired
    private FormsService formsService;
    @Autowired
    private QuestionsService questionsService;
    @Autowired
    private AnalysisService analysisService;


    //выбор вопросов
    @RequestMapping(method = RequestMethod.GET)
    public String indexAction(ModelMap model, @PathVariable("id") Integer id) {
        model.addAttribute("form", formsService.getForm(id));
        model.addAttribute("questions", questionsService.getQuestionByForm(id));
        //TODO SM
        return getView("index");
    }

    //Выбор статистик
    @RequestMapping(method = RequestMethod.GET, value = "/methods")
    public String MethodsAction(ModelMap model, @PathVariable("id") Integer id, HttpServletRequest request) {
        model.addAttribute("form", formsService.getForm(id));
        String [] selectQ = request.getParameterValues("question");
        ArrayList<QuestionsEntity> questions = new ArrayList<QuestionsEntity>();
        for(int i=0; i<selectQ.length; i++){
            questions.add(questionsService.getQuestion(Integer.parseInt(selectQ[i])));
        }
        model.addAttribute("questions", questions);
        return getView("methods");
    }

    // Результат анализа
    @RequestMapping(method = RequestMethod.GET, value = "/result")
    public String ResultAction(ModelMap model, @PathVariable("id") Integer id, HttpServletRequest request) {
        model.addAttribute("form", formsService.getForm(id));
        int idQuestion = Integer.parseInt(request.getParameter("idQuestion")); //код вопроса для анализа
        String [] types = request.getParameterValues("type"); //статистики для анализа
        Basic b = new Basic((AnalysisData)analysisService.getAnalysisData(idQuestion).toArray()[0], types);
        model.addAttribute("basic", b);
        return getView("result");
    }

    @Override
    protected String getViewPath() {
        return "analysis/basic";
    }
}
