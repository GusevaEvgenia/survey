package com.survey.mvc.model.analysis;

import com.survey.mvc.model.analysis.data.AnalysisData;
import com.survey.mvc.model.analysis.data.Answer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * Created by Belkin on 09.12.2014.
 */
public class Table extends Analysis  {
    private ArrayList<Integer> types;
    private String dependens;
    private String strengthLinks;
    private ArrayList<ArrayList<String>> res;

    public Table(Collection<AnalysisData> data, String[] types) {
        super(data);
        if(types!=null) {
            ArrayList<Integer> normalTypes = new ArrayList<Integer>();
            for (String type : types) {
                normalTypes.add(Integer.parseInt(type));
            }
            this.types = normalTypes;
        }
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
                    double percent = ((double)getFrequency(mainId, firstId)) / summ;
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
    private int getFrequency(long mainId, long firstId, long secondId) {
        ArrayList<Answer> mainData = getMainQuestionData().getAnswersWithOption(mainId);
        ArrayList<Answer> firstData = getFirstQuestionData().getAnswersWithOption(firstId);
        ArrayList<Answer> secondData = getSecondQuestionData().getAnswersWithOption(secondId);
        int count = 0;
        //TODO Написать это по нормальному!!!!( да-да, все еще стыдно=( )
        for(Answer a: mainData) {
            for(Answer firstA: firstData) {
                for(Answer secA: secondData) {
                    if(a.getIdCform() == firstA.getIdCform() && firstA.getIdCform() == secA.getIdCform()) {
                        count++;
                    }
                }
            }
        }
        return count;
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

    public AnalysisData getMainQuestionData() {
        return (AnalysisData) data.toArray()[0];
    }
    public AnalysisData getFirstQuestionData() {
        return (AnalysisData) data.toArray()[1];
    }
    public AnalysisData getSecondQuestionData() {
        return (AnalysisData) data.toArray()[2];
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

    public String getDependens(){
        if (dependens == null) {
            dependens = "dependens";
        }
        return dependens;
    }
    public String getStrengthLinks(){
        if (strengthLinks == null) {
            strengthLinks = "strengthLinks";
        }
        return strengthLinks;
    }
}
