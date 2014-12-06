package com.survey.mvc.model.analysis;

import java.util.ArrayList;

/**
 * Created by Belkin on 05.12.2014.
 */
public class Basic extends Analysis {
    Double average;

    public ArrayList<Integer> getTypes(){
        ArrayList<Integer> types = new ArrayList<Integer>();
        types.add(1);
        types.add(2);
        return types;
    }
    public String getScale(){
        return "nominal";
    }

    public double getAverage(){
        if (average == null) {
            average = 7.5;
        }
        return average;
    }
}
