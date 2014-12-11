package com.survey.mvc.model.analysis;

import com.survey.mvc.model.analysis.data.AnalysisData;

import java.util.Collection;
import java.util.HashMap;

/**
 * Created by Belkin on 05.12.2014.
 */
abstract public class Analysis {
    Collection<AnalysisData> data;

    HashMap<String, Long> cache;

    public Analysis(Collection<AnalysisData> data) {
        this();
        this.data = data;
    }

    public Analysis() {
        cache = new HashMap<String, Long>();
    }
}
