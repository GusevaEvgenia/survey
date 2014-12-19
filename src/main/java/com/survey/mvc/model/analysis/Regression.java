package com.survey.mvc.model.analysis;

import com.survey.mvc.model.analysis.data.AnalysisData;
import com.survey.mvc.model.analysis.data.Answer;
import com.survey.mvc.model.interfaces.IQuestion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * Created by Belkin on 12.12.2014.
 */
public class Regression  extends Analysis{
    private Basic basicX = new Basic((AnalysisData) getXQuestionData());
    private Basic basicY = new Basic((AnalysisData) getYQuestionData());
    private double averageX;
    private double averageY;
    private double a; //параметр а
    private double b; //параметр b
    private double tCount; //параметр t расчитаный
    private double tTable; //параметр t табличный
    private double important_level; // уровень значимости
    private double seb; //стандартное отклонение b
    private double corelletion; //кореляция
    private double determination; //детерминиция
    private double tParam; //
    private double f; //
    private ArrayList<HashMap<String, String>> answers;

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
            a = Math.round((getAverageY() - b * getAverageX()) * 100) / 100.00;
        }
        return a;
    }
    public double getB() {
        if(b == 0){
            int n = getAnswers().size();
            b = Math.round((sumXY() - n * (getAverageX()) * (getAverageY())) / (sumX2() - n * Math.pow(getAverageX(), 2)) * 100) / 100.00;
        }
        return b;
    }
    public double gettCount(){
        if(tCount == 0){
            tCount  =  Math.round((getB() / getSeb()) * 100) / 100.00;
        }
        return tCount;
    }
    public double gettCountMod(){
        return Math.abs(tCount);
    }
    public String gettTable(){
        int fredom = getAnswers().size()-2;
        ArrayList<HashMap<String, String>> tablXi = initTab();
        return tablXi.get(fredom-18).get(String.valueOf(important_level));
    }
    public double getCorelletion(){
        if(corelletion == 0){
            int n = getAnswers().size();
            corelletion = Math.round((
                    (n * sumXY() - sumX() * sumY()) /
                            Math.sqrt((n * sumX2() - Math.pow(sumX(), 2)) * (n * sumY2() - Math.pow(sumY(), 2)))
            ) * 100)/100.00;
        }
        return corelletion;
    }
    public double getDetermination(){
        if(determination == 0){
            if(corelletion == 0){
                corelletion = getCorelletion();
            }
            determination = Math.round(Math.pow(corelletion, 2) * 100)/100.00;
        }
        return determination;
    }
    public double gettParam(){
        int n = getAnswers().size();
        if(tParam == 0){
            if(determination == 0) {
                if(corelletion == 0){
                    corelletion = getCorelletion();

                }
                determination = getDetermination();
            }
            tParam = Math.round(corelletion * (Math.sqrt(n-2) / Math.sqrt(1-determination)) * 100) / 100.00;
        }
        return tParam;
    }
    public double gettParamMod(){
        return Math.abs(tParam);
    }
    public double getF(){
        if(f == 0) {
            if (determination == 0) {
                determination = getDetermination();
            }
        }
        int n = getAnswers().size();
        return Math.round(determination / (1 - determination) * (n - 2) *100) / 100.00;
    }
    public double getfMod(){
        return Math.abs(f);
    }
    //сделать массив
    public double getfTable(){
        return 4.11;
    }
    //видимо нужно сравнить с таблицей
    public double getDarbin(){
        return Math.rint(sumE2()/sumE_E12() *100) / 100.00;
    }
    public double getAcym(){
        return basicX.getAsymmetry();
    }
    public double getAcymParam(){
        double n = getAnswers().size();
        //return Math.round(3 * Math.sqrt(6 * (n - 1) / ((n + 1) * (n + 3))) * 100) / 100.00;
        return Math.round((3 * Math.sqrt(6 /n)) * 100) / 100.00;
    }
    public double getEx(){
        return basicX.getExcess();
    }
    public double getExParam(){
        double n = getAnswers().size();
        //return Math.round(5 * Math.sqrt(24 * n * (n - 2) * (n - 3) / (Math.pow(n - 1, 2) * (n + 3) * (n + 5))) * 100) / 100.00;
        return Math.round((6 * Math.sqrt(6 /n)) * 100) / 100.00;
    }

    public void setImportant_level(double important_level) {
        this.important_level = important_level;
    }

    private double sumX(){
        double sum = 0;
        for (HashMap<String, String> value : getAnswers()) {
            sum += Integer.parseInt(value.get("codeX"));
        }
        return sum;
    }
    private double sumY(){
        double sum = 0;
        for (HashMap<String, String> value : getAnswers()) {
            sum += Integer.parseInt(value.get("codeY"));
        }
        return sum;
    }
    private int sumXY(){
        int sum = 0;
        for (HashMap<String, String> value : getAnswers()) {
            sum += Integer.parseInt(value.get("codeX")) * Integer.parseInt(value.get("codeY"));
        }
        return sum;
    }
    private double sumX2(){
        double sum = 0;
        for (HashMap<String, String> value : getAnswers()) {
            sum += Math.pow(Integer.parseInt(value.get("codeX")), 2);
        }
        return sum;
    }
    private double sumY2(){
        double sum = 0;
        for (HashMap<String, String> value : getAnswers()) {
            sum += Math.pow(Integer.parseInt(value.get("codeY")), 2);
        }
        return sum;
    }
    private double getSeb() {
        if(seb == 0){
            int n = getAnswers().size();
            seb = Math.round((Math.sqrt((1 / sumX_averX2()) * (sumYi_Y() / (n - 2)))) * 100) / 100.00;
        }
        return seb;
    }
    private double sumX_averX2(){ //(xi - среднее x)^2
        double sum = 0;
        for (HashMap<String, String> value : getAnswers()) {
            sum += Math.pow(Integer.parseInt(value.get("codeX")) - getAverageX(), 2);
        }
        return Math.round(sum * 100)/100.00;
    }
    private double sumYi_Y(){ //(yi - yi^)^2
        double sum = 0;
        for (HashMap<String, String> value : getAnswers()) {
            double x = Integer.parseInt(value.get("codeX"));
            sum += Math.pow((Integer.parseInt(value.get("codeY")) - getY(x)), 2);
        }
        return Math.round(sum * 100)/100.00;
    }
    private double sumE2(){
        double sum = 0;
        for (HashMap<String, String> value : getAnswers()) {
            sum += Math.pow(Integer.parseInt(value.get("codeY")) - getY(Integer.parseInt(value.get("codeX"))), 2);
        }
        return Math.round(sum * 100) / 100.00;
    }
    //другая последовательность считает по разному
    private double sumE_E12(){
        double sum = 0;
        double e1 = 0;
        for (HashMap<String, String> value : getAnswers()) {
            if(e1 != 0){
                double e = Integer.parseInt(value.get("codeY")) - getY(Integer.parseInt(value.get("codeX")));
                sum += Math.pow(e - e1, 2);
            }
            e1 = Integer.parseInt(value.get("codeY")) - getY(Integer.parseInt(value.get("codeX")));

        }
        return Math.round(sum * 100) / 100.00;
    }

    private double getAverageX() {
        if(averageX == 0){
            //averageX = basicX.getAverage();
            averageX = sumX()/getAnswers().size();
        }
        return averageX;
    }
    private double getAverageY() {
        if(averageY == 0){
            //averageY = basicY.getAverage();
            averageY = sumY()/getAnswers().size();
        }
        return averageY;
    }
    private double getY(double x){
        return Math.round((getA() + getB() * x) * 100)/100.00;
    }

    //yi - зависимая переменная
    public AnalysisData getYQuestionData() {
        return (AnalysisData) data.toArray()[0];
    }
    //xi  - независимая переменнная
    public AnalysisData getXQuestionData() {
        return (AnalysisData) data.toArray()[1];
    }
    public ArrayList<HashMap<String, String>> getAnswers(){
        if(answers == null) {
            answers = new ArrayList<HashMap<String, String>>();
            for (HashMap<String, String> xAnsOpt : getXQuestionData().getQuestion().getAnswers()) {                           //Обход всех вариантов ответа X
                ArrayList<Answer> answersX = getXQuestionData().getAnswersWithOption(Long.parseLong(xAnsOpt.get("id")));      //Выбор всех ответов с вариантом ответа X
                for (HashMap<String, String> yAnsOpt : getYQuestionData().getQuestion().getAnswers()) {                       //Обход всех вариантов ответа Y
                    ArrayList<Answer> answersY = getYQuestionData().getAnswersWithOption(Long.parseLong(yAnsOpt.get("id")));  //Выбор всех ответов с вариантом ответа Y
                    for (Answer ansX : answersX) {                                                                            //Обход всех ответов с вариантом ответа X
                        for (Answer ansY : answersY) {                                                                        //Обход всех ответов с вариантом ответа Y
                            HashMap<String, String> answer = new HashMap<String, String>();
                            if (ansX.getIdCform() == ansY.getIdCform()) {
                                answer.put("idCForm", String.valueOf(ansY.getIdCform()));
                                answer.put("codeY", String.valueOf(ansY.getCode()));
                                answer.put("codeX", String.valueOf(ansX.getCode()));
                                answers.add(answer);
                                break;
                            }
                        }
                    }
                }
            }
        }
        return answers;
    }

    private ArrayList<HashMap<String, String>> initTab(){
        ArrayList<HashMap<String, String>> tablXi2 = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> row18 = new HashMap<String, String>();
        row18.put("0.25", "");
        row18.put("0.1", "");
        row18.put("0.05", "");
        row18.put("0.025", "");
        row18.put("0.01", "");
        row18.put("0.005", "");
        tablXi2.add(row18);
        HashMap<String, String> row19 = new HashMap<String, String>();
        row19.put("0.25", "");
        row19.put("0.1", "");
        row19.put("0.05", "");
        row19.put("0.025", "");
        row19.put("0.01", "");
        row19.put("0.005", "");
        tablXi2.add(row19);
        HashMap<String, String> row20 = new HashMap<String, String>();
        row20.put("0.25", "");
        row20.put("0.1", "");
        row20.put("0.05", "");
        row20.put("0.025", "");
        row20.put("0.01", "");
        row20.put("0.005", "");
        tablXi2.add(row20);
        HashMap<String, String> row21 = new HashMap<String, String>();
        row21.put("0.25", "");
        row21.put("0.1", "");
        row21.put("0.05", "");
        row21.put("0.025", "");
        row21.put("0.01", "");
        row21.put("0.005", "");
        tablXi2.add(row21);
        HashMap<String, String> row22 = new HashMap<String, String>();
        row22.put("0.25", "");
        row22.put("0.1", "");
        row22.put("0.05", "");
        row22.put("0.025", "");
        row22.put("0.01", "");
        row22.put("0.005", "");
        tablXi2.add(row22);
        HashMap<String, String> row23 = new HashMap<String, String>();
        row23.put("0.25", "");
        row23.put("0.1", "");
        row23.put("0.05", "");
        row23.put("0.025", "");
        row23.put("0.01", "");
        row23.put("0.005", "");
        tablXi2.add(row23);
        HashMap<String, String> row24 = new HashMap<String, String>();
        row24.put("0.25", "");
        row24.put("0.1", "");
        row24.put("0.05", "");
        row24.put("0.025", "");
        row24.put("0.01", "");
        row24.put("0.005", "");
        tablXi2.add(row24);
        HashMap<String, String> row25 = new HashMap<String, String>();
        row25.put("0.25", "");
        row25.put("0.05", "");
        row25.put("0.025", "");
        row25.put("0.01", "");
        row25.put("0.005", "");
        tablXi2.add(row25);
        HashMap<String, String> row26 = new HashMap<String, String>();
        row26.put("0.25", "");
        row26.put("0.1", "");
        row26.put("0.05", "");
        row26.put("0.025", "");
        row26.put("0.01", "");
        row26.put("0.005", "");
        tablXi2.add(row26);
        HashMap<String, String> row27 = new HashMap<String, String>();
        row27.put("0.25", "");
        row27.put("0.1", "");
        row27.put("0.05", "");
        row27.put("0.025", "");
        row27.put("0.01", "");
        row27.put("0.005", "");
        tablXi2.add(row27);
        HashMap<String, String> row28 = new HashMap<String, String>();
        row28.put("0.25", "");
        row28.put("0.1", "");
        row28.put("0.05", "");
        row28.put("0.025", "");
        row28.put("0.01", "");
        row28.put("0.005", "");
        tablXi2.add(row28);
        HashMap<String, String> row29 = new HashMap<String, String>();
        row29.put("0.25", "");
        row29.put("0.1", "");
        row29.put("0.05", "");
        row29.put("0.025", "");
        row29.put("0.01", "");
        row29.put("0.005", "");
        tablXi2.add(row29);
        HashMap<String, String> row30 = new HashMap<String, String>();
        row30.put("0.25", "");
        row30.put("0.1", "");
        row30.put("0.05", "");
        row30.put("0.025", "");
        row30.put("0.01", "");
        row30.put("0.005", "");
        tablXi2.add(row30);
        HashMap<String, String> row31 = new HashMap<String, String>();
        row31.put("0.25", "");
        row31.put("0.1", "");
        row31.put("0.05", "");
        row31.put("0.025", "");
        row31.put("0.01", "");
        row31.put("0.005", "");
        tablXi2.add(row31);
        HashMap<String, String> row32 = new HashMap<String, String>();
        row32.put("0.25", "");
        row32.put("0.1", "");
        row32.put("0.05", "");
        row32.put("0.025", "");
        row32.put("0.01", "");
        row32.put("0.005", "");
        tablXi2.add(row32);
        HashMap<String, String> row33 = new HashMap<String, String>();
        row33.put("0.25", "");
        row33.put("0.1", "");
        row33.put("0.05", "");
        row33.put("0.025", "");
        row33.put("0.01", "");
        row33.put("0.005", "");
        tablXi2.add(row33);
        HashMap<String, String> row34= new HashMap<String, String>();
        row34.put("0.25", "");
        row34.put("0.1", "");
        row34.put("0.05", "");
        row34.put("0.025", "");
        row34.put("0.01", "");
        row34.put("0.005", "");
        tablXi2.add(row34);
        HashMap<String, String> row35 = new HashMap<String, String>();
        row35.put("0.25", "");
        row35.put("0.1", "");
        row35.put("0.05", "");
        row35.put("0.025", "");
        row35.put("0.01", "");
        row35.put("0.005", "");
        tablXi2.add(row35);
        HashMap<String, String> row36 = new HashMap<String, String>();
        row36.put("0.25", "");
        row36.put("0.1", "");
        row36.put("0.05", "2.03");
        row36.put("0.025", "");
        row36.put("0.01", "");
        row36.put("0.005", "");
        tablXi2.add(row36);
        HashMap<String, String> row37= new HashMap<String, String>();
        row37.put("0.25", "");
        row37.put("0.1", "");
        row37.put("0.05", "");
        row37.put("0.025", "");
        row37.put("0.01", "");
        row37.put("0.005", "");
        tablXi2.add(row37);
        HashMap<String, String> row38 = new HashMap<String, String>();
        row38.put("0.25", "");
        row38.put("0.1", "");
        row38.put("0.05", "");
        row38.put("0.025", "");
        row38.put("0.01", "");
        row38.put("0.005", "");
        tablXi2.add(row38);
        HashMap<String, String> row = new HashMap<String, String>();
        row.put("0.25", "");
        row.put("0.1", "");
        row.put("0.05", "");
        row.put("0.025", "");
        row.put("0.01", "");
        row.put("0.005", "");
        tablXi2.add(row);
        return tablXi2;
    }

    /*private ArrayList<HashMap<String, String>> initTab(){
        ArrayList<HashMap<String, String>> tablXi2 = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> row1 = new HashMap<String, String>();
        row1.put("0.25", "");
        row1.put("0.1", "");
        row1.put("0.05", "");
        row1.put("0.025", "");
        row1.put("0.01", "");
        row1.put("0.005", "");
        tablXi2.add(row1);
        HashMap<String, String> row2 = new HashMap<String, String>();
        row2.put("0.25", "");
        row2.put("0.1", "");
        row2.put("0.05", "");
        row2.put("0.025", "");
        row2.put("0.01", "");
        row2.put("0.005", "");
        tablXi2.add(row2);
        HashMap<String, String> row3 = new HashMap<String, String>();
        row3.put("0.25", "");
        row3.put("0.1", "");
        row3.put("0.05", "");
        row3.put("0.025", "");
        row3.put("0.01", "");
        row3.put("0.005", "");
        tablXi2.add(row3);
        HashMap<String, String> row4 = new HashMap<String, String>();
        row4.put("0.25", "");
        row4.put("0.1", "");
        row4.put("0.05", "");
        row4.put("0.025", "");
        row4.put("0.01", "");
        row4.put("0.005", "");
        tablXi2.add(row4);
        HashMap<String, String> row5 = new HashMap<String, String>();
        row5.put("0.25", "");
        row5.put("0.1", "");
        row5.put("0.05", "");
        row5.put("0.025", "");
        row5.put("0.01", "");
        row5.put("0.005", "");
        tablXi2.add(row5);
        HashMap<String, String> row6 = new HashMap<String, String>();
        row6.put("0.25", "");
        row6.put("0.1", "");
        row6.put("0.05", "");
        row6.put("0.025", "");
        row6.put("0.01", "");
        row6.put("0.005", "");
        tablXi2.add(row6);
        HashMap<String, String> row7 = new HashMap<String, String>();
        row7.put("0.25", "");
        row7.put("0.1", "");
        row7.put("0.05", "");
        row7.put("0.025", "");
        row7.put("0.01", "");
        row7.put("0.005", "");
        tablXi2.add(row7);
        HashMap<String, String> row8 = new HashMap<String, String>();
        row8.put("0.25", "");
        row8.put("0.1", "");
        row8.put("0.05", "");
        row8.put("0.025", "");
        row8.put("0.01", "");
        row8.put("0.005", "");
        tablXi2.add(row8);
        HashMap<String, String> row9 = new HashMap<String, String>();
        row9.put("0.25", "");
        row9.put("0.1", "");
        row9.put("0.05", "");
        row9.put("0.025", "");
        row9.put("0.01", "");
        row9.put("0.005", "");
        tablXi2.add(row9);
        HashMap<String, String> row10 = new HashMap<String, String>();
        row10.put("0.25", "");
        row10.put("0.1", "");
        row10.put("0.05", "");
        row10.put("0.025", "");
        row10.put("0.01", "");
        row10.put("0.005", "");
        tablXi2.add(row10);
        HashMap<String, String> row11 = new HashMap<String, String>();
        row11.put("0.25", "");
        row11.put("0.1", "");
        row11.put("0.05", "");
        row11.put("0.025", "");
        row11.put("0.01", "");
        row11.put("0.005", "");
        tablXi2.add(row11);
        HashMap<String, String> row12 = new HashMap<String, String>();
        row12.put("0.25", "");
        row12.put("0.1", "");
        row12.put("0.05", "");
        row12.put("0.025", "");
        row12.put("0.01", "");
        row12.put("0.005", "");
        tablXi2.add(row12);
        HashMap<String, String> row13 = new HashMap<String, String>();
        row13.put("0.25", "");
        row13.put("0.1", "");
        row13.put("0.05", "");
        row13.put("0.025", "");
        row13.put("0.01", "");
        row13.put("0.005", "");
        tablXi2.add(row13);
        HashMap<String, String> row14 = new HashMap<String, String>();
        row14.put("0.25", "");
        row14.put("0.1", "");
        row14.put("0.05", "");
        row14.put("0.025", "");
        row14.put("0.01", "");
        row14.put("0.005", "");
        tablXi2.add(row14);
        HashMap<String, String> row15 = new HashMap<String, String>();
        row15.put("0.25", "");
        row15.put("0.1", "");
        row15.put("0.05", "");
        row15.put("0.025", "");
        row15.put("0.01", "");
        row15.put("0.005", "");
        tablXi2.add(row15);
        HashMap<String, String> row16 = new HashMap<String, String>();
        row16.put("0.25", "");
        row16.put("0.1", "");
        row16.put("0.05", "");
        row16.put("0.025", "");
        row16.put("0.01", "");
        row16.put("0.005", "");
        tablXi2.add(row16);
        HashMap<String, String> row17 = new HashMap<String, String>();
        row17.put("0.25", "");
        row17.put("0.1", "");
        row17.put("0.05", "");
        row17.put("0.025", "");
        row17.put("0.01", "");
        row17.put("0.005", "");
        tablXi2.add(row17);
        HashMap<String, String> row18 = new HashMap<String, String>();
        row18.put("0.25", "");
        row18.put("0.1", "");
        row18.put("0.05", "");
        row18.put("0.025", "");
        row18.put("0.01", "");
        row18.put("0.005", "");
        tablXi2.add(row18);
        HashMap<String, String> row19 = new HashMap<String, String>();
        row19.put("0.25", "");
        row19.put("0.1", "");
        row19.put("0.05", "");
        row19.put("0.025", "");
        row19.put("0.01", "");
        row19.put("0.005", "");
        tablXi2.add(row19);
        HashMap<String, String> row20 = new HashMap<String, String>();
        row20.put("0.25", "");
        row20.put("0.1", "");
        row20.put("0.05", "");
        row20.put("0.025", "");
        row20.put("0.01", "");
        row20.put("0.005", "");
        tablXi2.add(row20);
        HashMap<String, String> row30 = new HashMap<String, String>();
        row30.put("0.25", "");
        row30.put("0.1", "");
        row30.put("0.05", "");
        row30.put("0.025", "");
        row30.put("0.01", "");
        row30.put("0.005", "");
        tablXi2.add(row30);
        HashMap<String, String> row = new HashMap<String, String>();
        row.put("0.25", "");
        row.put("0.1", "");
        row.put("0.05", "");
        row.put("0.025", "");
        row.put("0.01", "");
        row.put("0.005", "");
        tablXi2.add(row);
        return tablXi2;
    }*/
}
