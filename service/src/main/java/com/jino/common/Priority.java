package com.jino.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Priority extends AbstractJiraObject {

    @SerializedName("name")
    @Expose
    private String name;

    public String getName() {
        return name;
    }
}
