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
                    double percent = ((double)getFrequency(mainId, firstId))/ summ; //
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
    private int getFrequency(long mainId, long firstId) {
        String cacheKey = "getFrequency::" + String.valueOf(mainId) + "__" + String.valueOf(firstId);
        Long response = cache.get(cacheKey);

        if(response == null) {
            ArrayList<Answer> mainData = getMainQuestionData().getAnswersWithOption(mainId);
            ArrayList<Answer> firstData = getFirstQuestionData().getAnswersWithOption(firstId);
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
        int fredom = (getRowsLength()-1)*(getColumnsLength()-1);
        return "getTableForXi2(fredom, a)";
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
}
