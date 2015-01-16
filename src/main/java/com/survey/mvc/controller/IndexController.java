package com.survey.mvc.controller;

import com.survey.mvc.entity.UsersEntity;
import com.survey.mvc.model.integration.ThirdPartySurvey;
import com.survey.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class IndexController extends AbstractController{

    @Autowired
    private UserService userService;

    @Autowired
    private ThirdPartySurvey surveyMonkey;

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

    /**
     * We use it for auth callback. We will be redirected here with code(token)
     * after auth on 3rd party server will be done
     * @param id
     * @param code
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "auth/{id:[0-9]+}/assign/callback")
    public @ResponseBody String assignCallbackAction(@PathVariable("id") Integer id, @RequestParam String code) {
        UsersEntity u = userService.getUser(id);
        u.setToken(code);
        userService.updateUser(u);
        return "<script>window.close();</script>";
    }

    /**
     * We use it to open popup and redirect to 3rd party auth url
     * @param id
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "auth/{id:[0-9]+}/assign")
    public String assignAction(@PathVariable("id") Integer id) {
        return "redirect:"+surveyMonkey.getAuthUrl(id);
    }

    /**
     * we use it to remove association with 3rd party api
     * @param id
     * @return
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "auth/{id:[0-9]+}/unassign")
    public @ResponseBody String unassignAction(@PathVariable("id") Integer id) {
        UsersEntity u = userService.getUser(id);
        u.setToken(null);
        userService.updateUser(u);
        return "redirect:/user/update";
    }

    //страница пользователя
    @RequestMapping(method = RequestMethod.GET, value = "/user")
    public String userAction(ModelMap model) {
        model.addAttribute("user", getCurrentUser());
        return getView("user");
    }

    //обновление данных о пользователе
    @RequestMapping(method = RequestMethod.GET, value = "/user/update")
    public String userUpdateAction(ModelMap model) {
        model.addAttribute("user", getCurrentUser());
        return getView("user");
    }


    @Override
    protected String getViewPath() {
        return "index";
    }
}
