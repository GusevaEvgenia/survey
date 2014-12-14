package com.survey.mvc.controller.analysis;

import com.survey.mvc.controller.AbstractController;
import com.survey.mvc.entity.FormsEntity;
import com.survey.mvc.model.integration.ThirdPartySurvey;
import com.survey.mvc.model.integration.model.Form;
import com.survey.mvc.service.FormsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Collection;

@Controller
@RequestMapping("/analysis")
public class AnalysisController extends AbstractController {

    @Autowired
    private FormsService formsService;
    @Autowired
    private ThirdPartySurvey surveyMonkey;

    @RequestMapping(method = RequestMethod.GET)
    public String indexAction(ModelMap model) {
        model.addAttribute("activeForms", formsService.getFormsByStatus("active"));
        model.addAttribute("archiveForms", formsService.getFormsByStatus("archive"));
        Collection<Form> form = new ArrayList<Form>();
        if(getCurrentUser().getToken()!=null){
            surveyMonkey.setToken(getCurrentUser().getToken());
            form = surveyMonkey.getForms();
        }
        model.addAttribute("smForms", form);
        return getView("index");
    }

    @Override
    protected String getViewPath() {
        return "analysis";
    }
}
