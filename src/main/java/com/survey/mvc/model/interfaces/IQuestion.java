package com.survey.mvc.model.interfaces;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Belkin on 09.12.2014.
 */
public interface IQuestion {

    public String getText();
    public ArrayList<HashMap<String, String>> getAnswers();
    public String getScale();
}
