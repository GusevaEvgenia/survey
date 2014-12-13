package com.survey.mvc.controller;

import com.survey.mvc.entity.UsersEntity;
import com.survey.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class IndexController extends AbstractController{

    @Autowired
    private UserService userService;

    //главная страница сайта
    @RequestMapping(method = RequestMethod.GET)
    public String indexAction(ModelMap model) {
        model.addAttribute("login", "true");
        return getView("index");
    }

    //
    @RequestMapping(method = RequestMethod.POST)
    public String registerAction(ModelMap model) {
        //model.addAttribute("login", "true");
        return getView("index");
    }

    @RequestMapping(method = RequestMethod.POST, value = "login")
    public String loginAction(HttpSession session, @RequestParam String username, @RequestParam String password ) {
        UsersEntity user = userService.tryLogin(username, password);
        if(user != null) {
            session.setAttribute("current_user", user.getIdUser());
            return "redirect:/forms";
        } else {
            return "redirect:/";
        }

    }

    //страница пользователя
    @RequestMapping(method = RequestMethod.GET, value = "/user")
    public String userAction(ModelMap model) {
        model.addAttribute("user", userService.getUser(1));
        return getView("user");
    }

    //обновление данных о пользователе
    @RequestMapping(method = RequestMethod.GET, value = "/user/update")
    public String userUpdateAction(ModelMap model) {
        model.addAttribute("user", userService.getUser(1));
        return getView("user");
    }


    @Override
    protected String getViewPath() {
        return "index";
    }
}
