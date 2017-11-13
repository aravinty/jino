package com.jino.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ChangeLog extends AbstractJiraObject {

    @SerializedName("items")
    @Expose
    private List<Items> items;

    public List<Items> getItems() {
        return items;
    }
}
