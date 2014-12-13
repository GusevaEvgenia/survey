package com.survey.mvc.controller;

import com.survey.mvc.entity.UsersEntity;
import com.survey.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;

public abstract class AbstractController {
    @Autowired
    HttpSession session;
    @Autowired
    private UserService userService;


    protected String getView(String viewName) {
        return getViewPath() + "/" + viewName;
    }

    protected abstract String getViewPath();

    /**
     * TODO use it instead of hardcoded userId
     * @return UsersEntity
     */
    public UsersEntity getCurrentUser() {
        return userService.getUser((Integer) session.getAttribute("current_user"));
    }

    /**
     * TODO check it in each action
     * @return boolean
     */
    public boolean isLogined() {
        return getCurrentUser() != null;
    }
}
