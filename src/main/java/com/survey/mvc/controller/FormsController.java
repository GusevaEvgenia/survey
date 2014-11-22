package com.survey.mvc.controller;

import com.survey.mvc.entity.FormsEntity;
import com.survey.mvc.service.FormsService;
import com.survey.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    // Просмотр каталога анкет
    @RequestMapping(method = RequestMethod.GET)
    public String indexAction(ModelMap model) {
        model.addAttribute("user", userService.getUser(1));
        model.addAttribute("active", formsService.getFormsByStatus("active"));
        model.addAttribute("new", formsService.getFormsByStatus("new"));
        model.addAttribute("archive", formsService.getFormsByStatus("archive"));
        model.addAttribute("monkey", 7);
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
        model.addAttribute("form", formsService.getForm(id));
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
        model.addAttribute("form", formsService.getForm(id));
        return getView("form/settings");
    }

    //Изменения настроек анкеты
    @RequestMapping(method = RequestMethod.POST, value = "/{id:[0-9]+}/settings")
    public String updateAction(@ModelAttribute("formUpdate") FormsEntity form, @PathVariable("id") Integer id) {
        formsService.updateForm(form);
        return "redirect:/forms/"+id+"/settings";
    }

    @Override
    protected String getViewPath() {
        return "forms";
    }
}
