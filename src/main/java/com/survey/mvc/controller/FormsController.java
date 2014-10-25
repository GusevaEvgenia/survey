package com.survey.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/forms")
public class FormsController extends AbstractController {

    // Просмотр каталога анкет
    @RequestMapping(method = RequestMethod.GET)
    public String indexAction(ModelMap model) {
        model.addAttribute("new", 5);
        model.addAttribute("archive", 3);
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
    public String createAction(ModelMap model) {
        return "redirect:/forms/123";
    }

    //Просмотр анкеты
    @RequestMapping(method = RequestMethod.GET, value = "/{id:[0-9]+}")
      public String showAction(ModelMap model, @PathVariable("id") Integer id) {
        model.addAttribute("formId", id);
        return getView("show");
    }

    @Override
    protected String getViewPath() {
        return "forms";
    }
}
