package com.survey.mvc.model.analysis;

import com.survey.mvc.model.analysis.data.AnalysisData;

import java.util.Collection;

/**
 * Created by Belkin on 05.12.2014.
 */
abstract public class Analysis {
    Collection<AnalysisData> data;

    public Analysis(Collection<AnalysisData> data) {
        this();
        this.data = data;
    }

    public Analysis() {
    }
}
