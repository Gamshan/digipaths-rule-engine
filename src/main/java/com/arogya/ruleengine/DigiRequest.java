package com.arogya.ruleengine;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class DigiRequest {

   private List<Object> obs;
   private Map<String,Object> additionalParams;

    public List<Object> getObs() {
        return obs;
    }

    public void setObs(List<Object> obs) {
        this.obs = obs;
    }

    public Map<String, Object> getAdditionalParams() {
        return additionalParams;
    }

    public void setAdditionalParams(Map<String, Object> additionalParams) {
        this.additionalParams = additionalParams;
    }
}
