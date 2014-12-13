package com.survey.mvc.model.analysis;

import com.survey.mvc.model.analysis.data.AnalysisData;
import com.survey.mvc.model.analysis.data.Answer;

import java.util.Collection;

/**
 * Created by Belkin on 12.12.2014.
 */
public class Regression  extends Analysis{
    private Basic basicX = new Basic((AnalysisData) getYQuestionData());
    private Basic basicY = new Basic((AnalysisData) getXQuestionData());
    private double averageX;
    private double averageY;
    private double a; //параметр а
    private double b; //параметр b

    public Regression(Collection<AnalysisData> data) {
        super(data);
    }
    public Regression(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public double getA() {
        if(a == 0){
            if(b==0){
                b = getB();
            }
            a = Math.floor((getAverageY() - b*getAverageX())*100)/100;
        }
        return a;
    }
    public double getB() {
        if(b == 0){
            int n = getYQuestionData().getAnswers().size();
            b = Math.floor((sumXY() - n*getAverageX()*getAverageY())/(sumX2() - n*Math.pow(getAverageX(), 2))*100)/100;
        }
        return b;
    }


    private int sumXY(){
        int sum = 0;
        int n = getYQuestionData().getAnswers().size();
        for (int i = 0; i<n; i++) {
            sum += getXQuestionData().getAnswers().get(i).getCode() + getYQuestionData().getAnswers().get(i).getCode();
        }
        return sum;
    }
    private double sumX2(){
        double sum = 0;
        int n = getYQuestionData().getAnswers().size();
        for (int i = 0; i<n; i++) {
            sum += Math.pow(getXQuestionData().getAnswers().get(i).getCode(), 2);
        }
        return sum;
    }

    private double getAverageX() {
        if(averageX == 0){
            averageX = basicX.getAverage();
        }
        return averageX;
    }
    private double getAverageY() {
        if(averageY == 0){
            averageY = basicY.getAverage();
        }
        return averageY;
    }

    //yi - зависимая переменная
    public AnalysisData getYQuestionData() {
        return (AnalysisData) data.toArray()[0];
    }
    //xi  - независимая переменнная
    public AnalysisData getXQuestionData() {
        return (AnalysisData) data.toArray()[1];
    }
}
