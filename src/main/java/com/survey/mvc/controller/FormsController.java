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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/forms")
public class FormsController extends AbstractController {

    @Autowired
    private UserService userService;
    @Autowired
    private FormsService formsService;
    @Autowired
    private CompletedFormsService completedFormsService;

    //Стариница каталога анкет
    @RequestMapping(method = RequestMethod.GET)
    public String indexAction(ModelMap model) {
        model.addAttribute("user", getCurrentUser());
        model.addAttribute("active", formsService.getFormsByStatus("active"));
        model.addAttribute("draft", formsService.getFormsByStatus("draft"));
        model.addAttribute("archive", formsService.getFormsByStatus("archive"));
        return getView("index");
    }

    //Страница создания анкеты
    @RequestMapping(method = RequestMethod.GET, value = "/new")
    public String newAction(ModelMap model) {
        model.addAttribute("user", getCurrentUser());
        return getView("new");
    }

    //Создание анкеты
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
        model.addAttribute("user", getCurrentUser());
        return getView("form/show");
    }

    //удаление анкеты
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id:[0-9]+}")
    public @ResponseBody String deleteAction(ModelMap model, @PathVariable("id") Integer id) {
        formsService.deleteForm(id);
        return "success";
    }

    //Страница изменения настроек анкеты
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
        if(form.getStatus().equals("archive")){
            formsService.deleteLink(id);
            formsService.setPicture("/uploads/archive.jpg", id);
            return "redirect:/forms/"+id;
        }else {
            return "redirect:/forms/" + id + "/settings";
        }
    }

    //страница сбора данных для анализа
    @RequestMapping(method = RequestMethod.GET, value = "/link/{hash:[0-9a-zA-Z.]+}")
    public String linkAction(ModelMap model, @PathVariable("hash") String hash) {
        FormsEntity form = formsService.getFormByLink(hash);
        model.addAttribute("form", form);
        model.addAttribute("designer", formsService.getDesignerByForm(form));
        model.addAttribute("btnSave", 1);
        return getView("link");
    }

    @RequestMapping(method = RequestMethod.POST, value = "/link/{hash:[0-9a-zA-Z.]+}")
    public String survaySaveAction(ModelMap model, @PathVariable("hash") String hash,@RequestParam Map<String,String> allRequestParams) {
        int idForm = formsService.getFormByLink(hash).getIdForm();
        ArrayList<Integer> options = getOptions(allRequestParams);
        completedFormsService.save(idForm, options);
        model.addAttribute("form", formsService.getForm(idForm));
        return "redirect:/forms/link/" + hash +"/thanks";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/link/{hash:[0-9a-zA-Z.]+}/thanks")
    public String survayThanksAction(ModelMap model, @PathVariable("hash") String hash) {
        int idForm = formsService.getFormByLink(hash).getIdForm();
        model.addAttribute("form", formsService.getForm(idForm));
        if(formsService.getForm(idForm).getMaximumForms()!=null) {
            if (completedFormsService.getCompletedFormsByForm(idForm).size() > formsService.getForm(idForm).getMaximumForms()) {
                formsService.setArchive(idForm);
            }
        }

        return getView("linkEnd");
    }

    //Изменение статуса анкеты на активная
    @RequestMapping(method = RequestMethod.GET, value = "/{id:[0-9]+}/setActive")
    public String setActiveAction(ModelMap model, @PathVariable("id") Integer id) {
        formsService.setActive(id);
        model.addAttribute("form", formsService.getForm(id));
        return "redirect:/forms/"+id;
    }



    @Override
    protected String getViewPath() {
        return "forms";
    }
    private ArrayList<Integer> getOptions( Map<String,String> allRequestParams ) {
        ArrayList<Integer> options = new ArrayList<Integer>();
        for(String key: allRequestParams.keySet()) {
            String val = allRequestParams.get(key);
            if(key.startsWith("option") && !val.equals("")) {
                options.add(Integer.parseInt(val));
            }
        }
        return options;
    }
}
