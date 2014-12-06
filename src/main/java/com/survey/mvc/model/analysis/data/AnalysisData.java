package com.survey.mvc.model.analysis.data;

/**
 * Created by Belkin on 05.12.2014.
 */
public class AnalysisData {
    Integer answer;
    String scale;
    Integer idQuestion;

    public AnalysisData(Integer answer, String scale, Integer idQuestion) {
        this();
        this.answer = answer;
        this.scale = scale;
        this.idQuestion = idQuestion;
    }

    public AnalysisData() {
    }
}
