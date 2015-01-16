package com.survey.mvc.model.analysis;

import com.survey.mvc.model.analysis.data.AnalysisData;
import com.survey.mvc.model.analysis.data.Answer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.StringJoiner;

/**
 * Created by Belkin on 09.12.2014.
 */
public class Table extends Analysis  {
    private ArrayList<Integer> types;
    private double dependens;
    private double strengthLinks;
    private ArrayList<ArrayList<String>> res;
    private double a;

    public Table(Collection<AnalysisData> data, String[] types, double a) {
        super(data);
        if(types!=null) {
            ArrayList<Integer> normalTypes = new ArrayList<Integer>();
            for (String type : types) {
                normalTypes.add(Integer.parseInt(type));
            }
            this.types = normalTypes;
        }
        this.a = a;
    }

    public ArrayList<Integer> getTypes(){
        return types;
    }

    private AnalysisData getAnaliseData(int index) {
        return (AnalysisData) data.toArray()[index];
    }

    //Вариационны ряд
    private ArrayList<ArrayList<String>> getVariationLine(){
        //FIXME кєш нас спасет, но вссе равно фигово это
        /*ArrayList<ArrayList<String>> frequency = new ArrayList<ArrayList<String>>();
        ArrayList<String> rowSumm = new ArrayList<String>();
        for(HashMap<String, String> mainQanswer :getMainQuestionData().getQuestion().getAnswers()) {
            ArrayList<String> row = new ArrayList<String>();
            long mainId = Long.parseLong(mainQanswer.get("id"));
            for(HashMap<String, String> firstQanswer: getFirstQuestionData().getQuestion().getAnswers()) {
                long firstId = Long.parseLong(firstQanswer.get("id"));
                int summ = getColumnSumm(firstId);
                if(summ == 0) {
                    row.add("0");
                } else {
                    double percent = ((double)getFrequency(mainId, firstId)) / summ;
                    double h = Math.ceil(percent*10000)/100;
                    row.add(String.valueOf(h));
                    rowSumm.add(String.valueOf(h));
                }
            }
            frequency.add(row);
        }
        frequency.add(rowSumm);*/
        ArrayList<ArrayList<String>> frequency = new ArrayList<ArrayList<String>>();
        for(HashMap<String, String> firstQanswer: getFirstQuestionData().getQuestion().getAnswers()) {
            long firstId = Long.parseLong(firstQanswer.get("id"));
            ArrayList<String> colum = new ArrayList<String>();
            double prc = 0;
            for(HashMap<String, String> mainQanswer :getMainQuestionData().getQuestion().getAnswers()) {
                long mainId = Long.parseLong(mainQanswer.get("id"));
                int summ = getColumnSumm(firstId);
                if(summ == 0) {
                    colum.add("0");
                } else {
                    double percent = ((double)getFrequency(mainId, firstId))/ summ;
                    double perc = Math.floor(percent*10000)/100;
                    colum.add(String.valueOf(perc));
                    prc += perc;
                }
            }
            colum.add(String.valueOf(Math.floor(prc*100)/100));
            frequency.add(colum);
        }

        return frequency;
    }
    private int getFrequency(long mainAnsId, long firstAnsId) {
        String cacheKey = "getFrequency::" + String.valueOf(mainAnsId) + "__" + String.valueOf(firstAnsId);
        Long response = cache.get(cacheKey);

        if(response == null) {
            ArrayList<Answer> mainData = getMainQuestionData().getAnswersWithOption(mainAnsId);
            ArrayList<Answer> firstData = getFirstQuestionData().getAnswersWithOption(firstAnsId);
            int count = 0;
            //TODO Написать это по нормальному!!!!( мне стыдно=( )
            for (Answer a : mainData) {
                for (Answer firstA : firstData) {
                    if (a.getIdCform() == firstA.getIdCform()) {
                        count++;
                    }
                }
            }
            response = (long) count;
            cache.put(cacheKey, response);

        }
        return response.intValue();
    }
    private int getColumnSumm(long firstId) {
        String cacheKey = "getColumnSumm::" + String.valueOf(firstId);
        Long result = cache.get(cacheKey);

        if(result == null) {
            ArrayList<HashMap<String, String>> answers = getMainQuestionData().getQuestion().getAnswers();
            int summ = 0;
            for (HashMap<String, String> answer : answers) {
                summ += getFrequency(Long.parseLong(answer.get("id")), firstId);
            }
            result = (long) summ;
        }
        return result.intValue();

    }
    private int getRowSumm(long mainId) {
        String cacheKey = "getRowSumm::" + String.valueOf(mainId);
        Long result = cache.get(cacheKey);

        if(result == null) {
            ArrayList<HashMap<String, String>> answers = getFirstQuestionData().getQuestion().getAnswers();
            int summ = 0;
            for (HashMap<String, String> answer : answers) {
                summ += getFrequency(mainId, Long.parseLong(answer.get("id")));
            }
            result = (long) summ;
        }
        return result.intValue();

    }

    public AnalysisData getMainQuestionData() {
        return (AnalysisData) data.toArray()[0];
    }
    public AnalysisData getFirstQuestionData() {
        return (AnalysisData) data.toArray()[1];
    }

    public ArrayList<ArrayList<String>> getAnswerOptions(){
        ArrayList<ArrayList<String>> answerOptions = new ArrayList<ArrayList<String>>();
        for(AnalysisData analys : data){
            ArrayList<String> options = new ArrayList<String>();
            for(HashMap<String, String> answer : analys.getQuestion().getAnswers()) {
                options.add(answer.get("text"));
            }
            answerOptions.add(options);
        }
        return answerOptions;
    }

    public String getCellVal(int q, int j) {
        if (res == null) {
            res = getVariationLine();
        }
        return res.get(q).get(j);
    }
    public int getRowsLength() {
        if (res == null) {
            res = getVariationLine();
        }
        return res.get(0).size();
    }
    public int getColumnsLength() {
        if (res == null) {
            res = getVariationLine();
        }
        return res.size();
    }

    public double getXi2Calc(){
        if (dependens == 0) {
            ArrayList<ArrayList<String>> fe = getFe();
            double summ = 0;
            int i = 0;
            for(HashMap<String, String> firstQanswer: getFirstQuestionData().getQuestion().getAnswers()) {
                long firstId = Long.parseLong(firstQanswer.get("id"));
                int j = 0;
                for(HashMap<String, String> mainQanswer :getMainQuestionData().getQuestion().getAnswers()) {
                    long mainId = Long.parseLong(mainQanswer.get("id"));
                    double val = Double.parseDouble(fe.get(i).get(j));
                    if(val != 0) {
                        summ += Math.floor((Math.pow((getFrequency(mainId, firstId) - val), 2) / val) * 100) / 100;
                    }
                    j++;
                }
                i++;
            }
            dependens = summ;
        }
        return dependens;
    }
    private ArrayList<ArrayList<String>> getFe(){
        ArrayList<ArrayList<String>> fe = new ArrayList<ArrayList<String>>();
        double n = getMainQuestionData().getAnswers().size() > getFirstQuestionData().getAnswers().size()
                ? getMainQuestionData().getAnswers().size()
                : getFirstQuestionData().getAnswers().size();
        for(HashMap<String, String> firstQanswer: getFirstQuestionData().getQuestion().getAnswers()) {
            long firstId = Long.parseLong(firstQanswer.get("id"));
            ArrayList<String> colum = new ArrayList<String>();
            for (HashMap<String, String> mainQanswer : getMainQuestionData().getQuestion().getAnswers()) {
                long mainId = Long.parseLong(mainQanswer.get("id"));
                double f = (double)getRowSumm(mainId) * (double) getColumnSumm(firstId) / n;
                colum.add(String.valueOf(Math.floor(f*100)/100));
            }
            fe.add(colum);
        }
        return fe;
    }
    public String getXi2Tabl(){
        int fredom = (getRowsLength()-2)*(getColumnsLength()-1);
        ArrayList<HashMap<String, String>> tablXi = initTab();
        return tablXi.get(fredom-1).get(String.valueOf(a));
    }

    public double getStrengthLinks(){
        if (strengthLinks == 0) {
            double n = getMainQuestionData().getAnswers().size() > getFirstQuestionData().getAnswers().size()
                    ? getMainQuestionData().getAnswers().size() : getFirstQuestionData().getAnswers().size();
            if(getColumnsLength()==2 && getRowsLength()==2){
                strengthLinks = Math.floor(Math.sqrt(getXi2Calc() / n) * 100) / 100;
            }else{
                strengthLinks = Math.floor(Math.sqrt(getXi2Calc() / (getXi2Calc() + n)) * 100) / 100;
            }
        }
        return strengthLinks;
    }


    private ArrayList<HashMap<String, String>> initTab(){
        ArrayList<HashMap<String, String>> tablXi2 = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> row1 = new HashMap<String, String>();
        row1.put("0.25", "1.323");
        row1.put("0.1", "2.706");
        row1.put("0.05", "3.841");
        row1.put("0.025", "5.024");
        row1.put("0.01", "6.635");
        row1.put("0.005", "7.879");
        tablXi2.add(row1);
        HashMap<String, String> row2 = new HashMap<String, String>();
        row2.put("0.25", "2.773");
        row2.put("0.1", "4.605");
        row2.put("0.05", "5.991");
        row2.put("0.025", "7.378");
        row2.put("0.01", "9.210");
        row2.put("0.005", "10.597");
        tablXi2.add(row2);
        HashMap<String, String> row3 = new HashMap<String, String>();
        row3.put("0.25", "4.108");
        row3.put("0.1", "6.251");
        row3.put("0.05", "7.815");
        row3.put("0.025", "9.348");
        row3.put("0.01", "11.345");
        row3.put("0.005", "12.838");
        tablXi2.add(row3);
        HashMap<String, String> row4 = new HashMap<String, String>();
        row4.put("0.25", "5.385");
        row4.put("0.1", "7.779");
        row4.put("0.05", "9.488");
        row4.put("0.025", "11.143");
        row4.put("0.01", "13.277");
        row4.put("0.005", "14.860");
        tablXi2.add(row4);
        HashMap<String, String> row5 = new HashMap<String, String>();
        row5.put("0.25", "6.626");
        row5.put("0.1", "9.236");
        row5.put("0.05", "11.071");
        row5.put("0.025", "12.833");
        row5.put("0.01", "15.086");
        row5.put("0.005", "16.750");
        tablXi2.add(row5);
        HashMap<String, String> row6 = new HashMap<String, String>();
        row6.put("0.25", "7.841");
        row6.put("0.1", "10.645");
        row6.put("0.05", "12.592");
        row6.put("0.025", "14.449");
        row6.put("0.01", "16.812");
        row6.put("0.005", "18.548");
        tablXi2.add(row6);
        HashMap<String, String> row7 = new HashMap<String, String>();
        row7.put("0.25", "9.037");
        row7.put("0.1", "12.017");
        row7.put("0.05", "14.067");
        row7.put("0.025", "16.013");
        row7.put("0.01", "18.475");
        row7.put("0.005", "20.278");
        tablXi2.add(row7);
        HashMap<String, String> row8 = new HashMap<String, String>();
        row8.put("0.25", "10.219");
        row8.put("0.1", "13.362");
        row8.put("0.05", "15.507");
        row8.put("0.025", "17.535");
        row8.put("0.01", "20.09");
        row8.put("0.005", "21.955");
        tablXi2.add(row8);
        HashMap<String, String> row9 = new HashMap<String, String>();
        row9.put("0.25", "11.389");
        row9.put("0.1", "14.684");
        row9.put("0.05", "16.919");
        row9.put("0.025", "19.023");
        row9.put("0.01", "21.666");
        row9.put("0.005", "23.589");
        tablXi2.add(row9);
        HashMap<String, String> row10 = new HashMap<String, String>();
        row10.put("0.25", "12.549");
        row10.put("0.1", "15.987");
        row10.put("0.05", "18.307");
        row10.put("0.025", "20.483");
        row10.put("0.01", "23.209");
        row10.put("0.005", "25.188");
        tablXi2.add(row10);
        HashMap<String, String> row11 = new HashMap<String, String>();
        row11.put("0.25", "13.701");
        row11.put("0.1", "17.275");
        row11.put("0.05", "19.675");
        row11.put("0.025", "21.920");
        row11.put("0.01", "24.725");
        row11.put("0.005", "26.757");
        tablXi2.add(row11);
        HashMap<String, String> row12 = new HashMap<String, String>();
        row12.put("0.25", "14.845");
        row12.put("0.1", "18.549");
        row12.put("0.05", "21.026");
        row12.put("0.025", "23.337");
        row12.put("0.01", "26.217");
        row12.put("0.005", "28.299");
        tablXi2.add(row12);
        HashMap<String, String> row13 = new HashMap<String, String>();
        row13.put("0.25", "15.984");
        row13.put("0.1", "19.812");
        row13.put("0.05", "22.362");
        row13.put("0.025", "24.736");
        row13.put("0.01", "27.688");
        row13.put("0.005", "29.819");
        tablXi2.add(row13);
        HashMap<String, String> row14 = new HashMap<String, String>();
        row14.put("0.25", "17.117");
        row14.put("0.1", "21.064");
        row14.put("0.05", "23.685");
        row14.put("0.025", "26.119");
        row14.put("0.01", "29.141");
        row14.put("0.005", "31.319");
        tablXi2.add(row14);
        HashMap<String, String> row15 = new HashMap<String, String>();
        row15.put("0.25", "18.245");
        row15.put("0.1", "22.307");
        row15.put("0.05", "24.996");
        row15.put("0.025", "27.488");
        row15.put("0.01", "30.578");
        row15.put("0.005", "32.801");
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
        row18.put("0.25", "21.605");
        row18.put("0.1", "25.989");
        row18.put("0.05", "28.869");
        row18.put("0.025", "31.526");
        row18.put("0.01", "34.805");
        row18.put("0.005", "37.156");
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
        row20.put("0.25", "23.328");
        row20.put("0.1", "28.412");
        row20.put("0.05", "31.410");
        row20.put("0.025", "34.170");
        row20.put("0.01", "37.566");
        row20.put("0.005", "39.997");
        tablXi2.add(row20);
        HashMap<String, String> row21 = new HashMap<String, String>();
        row21.put("0.25", "23.328");
        row21.put("0.1", "28.412");
        row21.put("0.05", "31.410");
        row21.put("0.025", "34.170");
        row21.put("0.01", "37.566");
        row21.put("0.005", "39.997");
        tablXi2.add(row21);
        HashMap<String, String> row22 = new HashMap<String, String>();
        row22.put("0.25", "23.328");
        row22.put("0.1", "28.412");
        row22.put("0.05", "31.410");
        row22.put("0.025", "34.170");
        row22.put("0.01", "37.566");
        row22.put("0.005", "39.997");
        tablXi2.add(row22);
        HashMap<String, String> row23 = new HashMap<String, String>();
        row23.put("0.25", "23.328");
        row23.put("0.1", "28.412");
        row23.put("0.05", "31.410");
        row23.put("0.025", "34.170");
        row23.put("0.01", "37.566");
        row23.put("0.005", "39.997");
        tablXi2.add(row23);
        HashMap<String, String> row24 = new HashMap<String, String>();
        row24.put("0.25", "");
        row24.put("0.1", "33.196");
        row24.put("0.05", "36.42");
        row24.put("0.025", "");
        row24.put("0.01", "42.989");
        row24.put("0.005", "");
        tablXi2.add(row24);
        HashMap<String, String> row30 = new HashMap<String, String>();
        row30.put("0.25", "34.8");
        row30.put("0.1", "40.256");
        row30.put("0.05", "43.773");
        row30.put("0.025", "46.979");
        row30.put("0.01", "50.892");
        row30.put("0.005", "53.672");
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
    }
}
