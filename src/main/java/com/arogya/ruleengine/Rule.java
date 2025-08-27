package com.arogya.ruleengine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Rule {
    private String function;

    private ArrayList<Object> obs;


    private List<String> requiredAdditionalFields;

    private String hideCondition;

    private String message;

    private String recommendation;


    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public ArrayList<Object> getObs() {
        return obs;
    }

    public void setObs(ArrayList<Object> obs) {
        this.obs = obs;
    }

    public List<String> getRequiredAdditionalFields() {
        return requiredAdditionalFields;
    }

    public void setRequiredAdditionalFields(List<String> requiredAdditionalFields) {
        this.requiredAdditionalFields = requiredAdditionalFields;
    }

    public String getHideCondition() {
        return hideCondition;
    }

    public void setHideCondition(String hideCondition) {
        this.hideCondition = hideCondition;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }
}
