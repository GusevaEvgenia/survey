package com.survey.mvc.model.analysis;

import java.util.ArrayList;

/**
 * Created by Belkin on 05.12.2014.
 */
public class Basic extends Analysis {
    Double average;
    Double moda;
    Double median;
    Double variationScale;
    Double interquartile;
    Double dispersion;
    Double deviation;
    Double variation;
    Double asymmetry;
    Double excess;

    public ArrayList<Integer> getTypes(){
        ArrayList<Integer> types = new ArrayList<Integer>();
        types.add(1);
        types.add(2);
        return types;
    }
    public String getScale(){
        return "nominal";
    }

    //Вариационны ряд
    public ArrayList<ArrayList<String>> getFrequency(){
        ArrayList<ArrayList<String>> frequency = new ArrayList<ArrayList<String>>();
        ArrayList<String> row = new ArrayList<String>();
        row.add("Ответ1");
        row.add("2");
        row.add("0.4");
        frequency.add(row);
        return frequency;
    }

    //Среднее арифметическое 2
    public double getAverage(){
        if (average == null) {
            average = 7.5;
        }
        return average;
    }

    //Мода 3
    public double getModa(){
        if (moda == null) {
            moda = 7.5;
        }
        return moda;
    }

    //Медиана 4
    public double getMedian(){
        if (median == null) {
            median = 7.5;
        }
        return median;
    }

    //Размах вариации 5
    public double getVariationScale(){
        if (variationScale == null) {
            variationScale = 7.5;
        }
        return variationScale;
    }

    //Межкватериальный размах 6
    public double getInterquartile (){
        if (interquartile  == null) {
            interquartile  = 7.5;
        }
        return interquartile ;
    }

    //Дисперсия 7
    public double getDispersion(){
        if (dispersion  == null) {
            dispersion  = 7.5;
        }
        return dispersion ;
    }

    //Среднекватериальное отклонение 8
    public double getDeviation(){
        if (deviation  == null) {
            deviation  = 7.5;
        }
        return deviation ;
    }

    //Коэфициент вариации 9
    public double getVariation(){
        if (variation  == null) {
            variation  = 7.5;
        }
        return variation;
    }

    //Ассиметрия 10
    public double getAsymmetry(){
        if (asymmetry  == null) {
            asymmetry  = 7.5;
        }
        return asymmetry ;
    }

    //Эксцесс 11
    public double getExcess(){
        if (excess  == null) {
            excess  = 7.5;
        }
        return excess;
    }




}
