package com.survey.mvc.controller;

import com.survey.mvc.entity.FormsEntity;
import com.survey.mvc.entity.UsersEntity;
import com.survey.mvc.model.integration.SurveyMonkey;
import com.survey.mvc.model.integration.ThirdPartySurvey;
import com.survey.mvc.service.CompletedFormsService;
import com.survey.mvc.service.FormsService;
import com.survey.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/forms")
public class FormsController extends AbstractController {

    @Autowired
    private UserService userService;
    @Autowired
    private FormsService formsService;
    @Autowired
    private CompletedFormsService completedFormsService;

    // Просмотр каталога анкет
    @RequestMapping(method = RequestMethod.GET)
    public String indexAction(ModelMap model) {
        UsersEntity user = userService.getUser(1);
        model.addAttribute("user", user);
        model.addAttribute("active", formsService.getFormsByStatus("active"));
        model.addAttribute("new", formsService.getFormsByStatus("new"));
        model.addAttribute("archive", formsService.getFormsByStatus("archive"));
        return getView("index");
    }

    // Форма создания анкеты
    @RequestMapping(method = RequestMethod.GET, value = "/new")
    public String newAction(ModelMap model) {
        return getView("new");
    }

    //Создание и изменение анкеты
    @RequestMapping(method = RequestMethod.POST)
    public String createAction(@ModelAttribute("form") FormsEntity form) {
        formsService.addForm(form);
        List<FormsEntity> forms = formsService.getForms();
        int p=0;
        for (int i=0; i<forms.size()-1;i++){
            int j = forms.get(i).getIdForm();
            int k = forms.get(i+1).getIdForm();
            if(j<k)
                p=k;
        }
        return "redirect:/forms/"+p;
    }

    //Просмотр анкеты
    @RequestMapping(method = RequestMethod.GET, value = "/{id:[0-9]+}")
      public String showAction(ModelMap model, @PathVariable("id") Integer id) {
        model.addAttribute("newAnsEx", completedFormsService.newAnswersExist(id));
        model.addAttribute("form", formsService.getLoadedForm(id));
        model.addAttribute("designer", formsService.getDesignerByFormId(id));
        model.addAttribute("user", userService.getUser(1));
        return getView("form/show");
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id:[0-9]+}")
    public @ResponseBody String deleteAction(ModelMap model, @PathVariable("id") Integer id) {
        formsService.deleteForm(id);
        return "success";
    }

    // Форма изменения настроек анкеты
    @RequestMapping(method = RequestMethod.GET, value = "/{id:[0-9]+}/settings")
    public String updateFormAction(ModelMap model, @PathVariable("id") Integer id) {
        model.addAttribute("newAnsEx", completedFormsService.newAnswersExist(id));
        model.addAttribute("form", formsService.getForm(id));
        return getView("form/settings");
    }

    //Изменения настроек анкеты
    @RequestMapping(method = RequestMethod.POST, value = "/{id:[0-9]+}/settings")
    public String updateAction(@ModelAttribute("formUpdate") FormsEntity form, @PathVariable("id") Integer id) {
        formsService.updateForm(form);
        return "redirect:/forms/"+id+"/settings";
    }

    @RequestMapping(value = "/link/{hash:[0-9a-zA-Z.]+}")
    public String linkAction(ModelMap model, @PathVariable("hash") String hash) {
        FormsEntity form = formsService.getFormByLink(hash);
        model.addAttribute("form", form);
        model.addAttribute("designer", formsService.getDesignerByForm(form));
        return getView("link");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id:[0-9]+}/public")
    public String publicationFormAction(ModelMap model, @PathVariable("id") Integer id) {
        formsService.publication(id);
        model.addAttribute("form", formsService.getForm(id));
        return "redirect:/forms/"+id;
    }

    @Override
    protected String getViewPath() {
        return "forms";
    }
}
