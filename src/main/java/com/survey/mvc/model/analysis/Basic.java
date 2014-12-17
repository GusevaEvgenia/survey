package com.survey.mvc.model.analysis;

import com.survey.mvc.model.analysis.data.AnalysisData;
import com.survey.mvc.model.analysis.data.Answer;
import com.survey.mvc.model.interfaces.IQuestion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

/**
 * Created by Belkin on 05.12.2014.
 */
public class Basic extends Analysis {
    public static final String SCALE_NOMINAL = "nominal";
    public static final String SCALE_RATIO = "ratio";
    public static final String SCALE_ORDINAL = "ordinal";
    public static final String SCALE_INTERVAL = "interval";
    public static final Integer[] NOMINAL_AVAILABLE  = {1, 2, 4, 5, 6, 7, 8, 9, 10, 11 };
    public static final Integer[] RATIO_AVAILABLE    = {1, 4, 10, 11};
    public static final Integer[] ORDINAL_AVAILABLE  = {1, 2, 4, 5, 6, 7, 8, 9, 10, 11};
    public static final Integer[] INTERVAL_AVAILABLE = {1, 3, 10, 11};

    private ArrayList<Integer> types = new ArrayList<Integer>();
    private Double average;
    private long moda;
    private Double median;
    private Double variationScale;
    private Double interQuartile;
    private Double dispersion;
    private Double deviation;
    private Double variation;
    private Double asymmetry;
    private Double excess;
    private ArrayList<ArrayList<String>> variationLine;
    private AnalysisData analysisData;


    public Basic(AnalysisData analysisData){
        this.analysisData = analysisData;
    }
    public Basic(AnalysisData data, String[] types) {
        this(data);
        ArrayList<Integer> normalTypes = new ArrayList<Integer>();
        for (String type : types) {
            normalTypes.add(Integer.parseInt(type));
        }
        setTypes(normalTypes);
    }
    /*public Basic(Collection<AnalysisData> data, Integer[] types) {
        super(data);
        setTypes(new ArrayList<Integer>(Arrays.asList(types)));
    }*/

    public ArrayList<Integer> getTypes(){
        return types;
    }
    private void setTypes(ArrayList<Integer> types) {
        ArrayList<Integer> response = new ArrayList<Integer>();
        Collection<Integer> available = getAvailableTypes();
        for(Integer type: types) {
            if(available.contains(type)) {
                response.add(type);
            }
        }
        this.types = response;
    }

    private Collection<Integer> getAvailableTypes() {
        Collection<Integer> response;
        String scale = getScale();
        if(scale.equals(SCALE_NOMINAL)) {
            response = Arrays.asList(NOMINAL_AVAILABLE);
        } else if(scale.equals(SCALE_RATIO)) {
            response = Arrays.asList(RATIO_AVAILABLE);
        } else if(scale.equals(SCALE_INTERVAL)) {
            response = Arrays.asList(INTERVAL_AVAILABLE);
        } else if(scale.equals(SCALE_ORDINAL)) {
            response = Arrays.asList(ORDINAL_AVAILABLE);
        } else {
            response = new ArrayList<Integer>();
        }
        return response;
    }

    public String getScale(){
        return getAnaliseData().getQuestion().getScale();
    }

    private AnalysisData getAnaliseData() {
        return (AnalysisData) analysisData;
    }

    //Вариационны ряд
    public ArrayList<ArrayList<String>> getVariationLine(){
        if(variationLine == null) {
            int size = getAnaliseData().getAnswers().size();
            double pctSum = 0;
            ArrayList<ArrayList<String>> frequency = new ArrayList<ArrayList<String>>();
            for (HashMap<String, String> answerOption : getAnaliseData().getQuestion().getAnswers()) {
                double count = getFrequency(Long.parseLong(answerOption.get("id")));
                double pct = Math.floor(count/size*10000)/100;
                pctSum += pct;
                ArrayList<String> row = new ArrayList<String>();
                row.add(answerOption.get("text") != null ? answerOption.get("text") : null);
                row.add("" + (int) count);
                row.add(pct + "%");
                frequency.add(row);
            }
            ArrayList<String> row = new ArrayList<String>();
            row.add("Сумма");
            row.add("" + size);
            row.add(Math.floor(pctSum * 100) / 100 + "%");
            frequency.add(row);
            variationLine=frequency;
        }
        return variationLine;
    }

    private int getFrequency(long id){
        String key = "getFrequency::"+id;
        Long value = cache.get(key);
        if(value == null){
            value = (long) getAnaliseData().getAnswersWithOption(id).size();
        }
        return value.intValue();
    }

    //Среднее арифметическое 2
    public double getAverage(){
        if (average == null) {
            int size = getAnaliseData().getAnswers().size();
            double count = 0;
            for (HashMap<String, String> answer : getAnaliseData().getQuestion().getAnswers()) {
                int c = Integer.parseInt(answer.get("order")) * getFrequency(Long.parseLong(answer.get("id")));
                count += c;
            }
            average = Math.floor(count/size*100)/100;
        }
        return average;
    }

    //Мода 3 НЕ протестированно
    public long getModa(){
        if (moda == 0) {
            int m = 0;
            for (HashMap<String, String> answer : getAnaliseData().getQuestion().getAnswers()) {
                int f = getFrequency(Long.parseLong(answer.get("id")));
                if(f>m){
                    m=f;
                    moda = Long.parseLong(answer.get("id"));
                }
            }
        }
        return moda;
    }

    //Медиана 4
    public double getMedian(){
        if (median == null) { //четное число
            double size = getAnaliseData().getQuestion().getAnswers().size();
            double midel = size%2;
            if(midel==0){
                median = (size+1)/2;
            }else {
                median = (double) Math.round(size/2);
            }
        }
        return median;
    }

    //Размах вариации 5 Все варианты или только с ответами?
    public double getVariationScale(){
        if (variationScale == null) {
            int first = Integer.parseInt(getAnaliseData().getQuestion().getAnswers().get(0).get("id"));
            int size = getAnaliseData().getQuestion().getAnswers().size();
            int last = Integer.parseInt(getAnaliseData().getQuestion().getAnswers().get(size - 1).get("id"));
            variationScale = (double) last-first;
        }
        return variationScale;
    }

    //Межкватериальный размах 6
    public double getInterQuartile (){
        if (interQuartile == null) {
            int size = getAnaliseData().getQuestion().getAnswers().size();
            double prc75 = (int)(size*0.75);
            double prc25 = (int)(size*0.25)+1;
            interQuartile = prc75 - prc25;
        }
        return interQuartile;
    }

    //Дисперсия 7
    public double getDispersion(){
        if (dispersion  == null) {
            dispersion = (double) Math.round(centrMoment(2) / (getAnaliseData().getAnswers().size() - 1) * 100) / 100;
        }
        return dispersion ;
    }

    //Среднекватериальное отклонение 8
    public double getDeviation(){
        if (deviation  == null) {
            deviation  = (double) Math.round(Math.sqrt(getDispersion()) *100) / 100;
        }
        return deviation ;
    }

    //Коэфициент вариации 9
    public double getVariation(){
        if (variation  == null) {
            variation  = (double) Math.round(getDeviation() / getAverage() *100) / 100;
        }
        return variation;
    }

    //Ассиметрия 10
    public double getAsymmetry(){
        if (asymmetry  == null) {
            asymmetry  = (double) Math.round(centrMoment(3) / getAnaliseData().getAnswers().size() / Math.pow(getDeviation(), 3) * 100) / 100;
        }
        return asymmetry ;
    }

    //Эксцесс 11
    public double getExcess(){
        if (excess  == null) {
            excess  = (double) Math.round((centrMoment(4) / getAnaliseData().getAnswers().size() / Math.pow(getDeviation(), 4) - 3) * 100) / 100;
        }
        return excess;
    }

    private double centrMoment(int s){
        double av = getAverage();
        double summ = 0;
        for (HashMap<String, String> answer : getAnaliseData().getQuestion().getAnswers()) {
            int f = getFrequency(Long.parseLong(answer.get("id")));
            summ += Math.pow((Integer.parseInt(answer.get("order"))-av), s)*f;
        }
        return summ;
    }

    public IQuestion getQuestion() {
        return getAnaliseData().getQuestion();
    }
}
