package com.jino.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Fields {

    @SerializedName("priority")
    @Expose
    private Priority priority;

    @SerializedName("status")
    @Expose
    private Status status;

    @SerializedName("summary")
    @Expose
    private String summary;

    @SerializedName("creator")
    @Expose
    private User creator;

    @SerializedName("reporter")
    @Expose
    private User reporter;

    @SerializedName("assignee")
    @Expose
    private User assignee;


    public Priority getPriority() {
        return priority;
    }

    public Status getStatus() {
        return status;
    }

    public String getSummary() {
        return summary;
    }

    public User getCreator() {
        return creator;
    }

    public User getReporter() {
        return reporter;
    }

    public User getAssignee() {
        return assignee;
    }

}