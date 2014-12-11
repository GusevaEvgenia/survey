package com.survey.mvc.model.analysis;

import com.survey.mvc.model.analysis.data.AnalysisData;
import com.survey.mvc.model.analysis.data.Answer;

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
    private Double moda;
    private Double median;
    private Double variationScale;
    private Double interQuartile;
    private Double dispersion;
    private Double deviation;
    private Double variation;
    private Double asymmetry;
    private Double excess;

    public Basic(Collection<AnalysisData> data, String[] types) {
        super(data);
        ArrayList<Integer> normalTypes = new ArrayList<Integer>();
        for (String type : types) {
            normalTypes.add(Integer.parseInt(type));
        }
        setTypes(normalTypes);
    }

    public Basic(Collection<AnalysisData> data, Integer[] types) {
        super(data);
        setTypes(new ArrayList<Integer>(Arrays.asList(types)));
    }

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
        return (AnalysisData) data.toArray()[0];
    }

    //Вариационны ряд
    public ArrayList<ArrayList<String>> getVariationLine(){
        int size = getAnaliseData().getAnswers().size();
        double pctSum = 0;
        ArrayList<ArrayList<String>> frequency = new ArrayList<ArrayList<String>>();
        for(HashMap<String, String> answerOption : getAnaliseData().getQuestion().getAnswers()) {
            double count = getFrequency(Long.parseLong(answerOption.get("id")));
            double pct = count/size;
            double pct1 = (int) (pct*10000);
            double pct2 = pct1/100;
            //double perct = Math.rint(pct*10000)/100;
            //double jj = Math.rint(100.0 * pct) / 100.0;
            pctSum += pct2;
            ArrayList<String> row = new ArrayList<String>();
            row.add(answerOption.get("text")!=null ? answerOption.get("text") : null); //TODO найти вариаты на числовой вопрос
            row.add("" + (int)count);
            row.add(pct2+"%");
            frequency.add(row);
        }
        ArrayList<String> row = new ArrayList<String>();
        row.add("Сумма");
        row.add(""+size);
        row.add(Math.rint(pctSum*100)/100 + "%");
        frequency.add(row);
        return frequency;
    }

    public int getFrequency(long id){
        return getAnaliseData().getAnswersWithOption(id).size();
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
    public double getInterQuartile (){
        if (interQuartile == null) {
            interQuartile = 7.5;
        }
        return interQuartile;
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
