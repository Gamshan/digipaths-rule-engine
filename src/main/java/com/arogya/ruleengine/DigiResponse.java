package com.arogya.ruleengine;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class DigiResponse {

    private Date date;
    private String message;

    private String recommendation;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
