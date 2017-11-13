package com.jino.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Issue extends AbstractJiraObject {

    @SerializedName("key")
    @Expose
    private String key;


    @SerializedName("fields")
    @Expose
    private Fields fields;

    
    public String getKey() {
        return key;
    }

    public Fields getFields() {
        return fields;
    }
}
