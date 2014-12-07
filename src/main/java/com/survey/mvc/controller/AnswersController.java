package com.survey.mvc.controller;

import com.survey.mvc.service.AnswersService;
import com.survey.mvc.service.CompletedFormsService;
import com.survey.mvc.service.FormsService;
import com.survey.mvc.service.QuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("forms/{id:[0-9]+}/answers")
public class AnswersController extends AbstractController {

    @Autowired
    private QuestionsService questionsService;
    @Autowired
    private AnswersService answersService;
    @Autowired
    private CompletedFormsService completedFormsService;
    @Autowired
    private FormsService formsService;


    @RequestMapping(method = RequestMethod.GET)
    public String indexAction(ModelMap model, @PathVariable("id") Integer id) {
        model.addAttribute("newAnsEx", completedFormsService.newAnswersExist(id));
        model.addAttribute("statuses", answersService.getStatuses());
        model.addAttribute("currentStatus", "all");
        model.addAttribute("questions", questionsService.getQuestionByForm(id));
        model.addAttribute("answers", answersService.getAnswersByCompletedForm(id));
        model.addAttribute("form", formsService.getForm(id));
        return getView("answers");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{type:[a-zA-Z]+}")
    public String filtratedIndexAction(ModelMap model, @PathVariable("id") Integer id, @PathVariable("type") String type) {
        model.addAttribute("newAnsEx", completedFormsService.newAnswersExist(id));
        model.addAttribute("questions", questionsService.getQuestionByForm(id));
        // model.addAttribute("complForms", completedFormsService.getCompletedFormsByForm(id));
        model.addAttribute("answers", answersService.getAnswersByCompletedForm(id, type));
        model.addAttribute("statuses", answersService.getStatuses());
        model.addAttribute("currentStatus", type);
        model.addAttribute("form", formsService.getForm(id));
        return getView("answers");
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{idCF:[0-9]+}")
    public String updateStatusAction(ModelMap model, HttpServletRequest request, @PathVariable("id") Integer id,
                                     @PathVariable("idCF") Integer idCF, @RequestParam String status) {
        completedFormsService.updateStatus(idCF, status);
        model.addAttribute("form", formsService.getForm(id));
        return getView("answers");
    }

    @Override
    protected String getViewPath() {
        return "forms/form";
    }
}
