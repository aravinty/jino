package com.jino.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public abstract class AbstractJiraObject {

    @SerializedName("self")
    @Expose
    private String self;

    @SerializedName("id")
    @Expose
    private String id;

    public String getSelf() {
        return self;
    }

    public String getId() {
        return id;
    }
}
