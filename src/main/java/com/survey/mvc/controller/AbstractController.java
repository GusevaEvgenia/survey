package com.survey.mvc.controller;

public abstract class AbstractController {

    protected String getView(String viewName) {
        return getViewPath() + "/" + viewName;
    }

    protected abstract String getViewPath();
}
