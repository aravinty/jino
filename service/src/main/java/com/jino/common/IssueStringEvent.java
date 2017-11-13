package com.jino.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IssueStringEvent  extends AbstractJiraEvent {

    @SerializedName("issue_event_type_name")
    @Expose
    private String issueEventTypeName;

    @SerializedName("issue")
    @Expose
    private Issue issue;

    @SerializedName("comment")
    @Expose
    private Comment comment;

    @SerializedName("changelog")
    @Expose
    private ChangeLog changelog;

    @SerializedName("user")
    @Expose
    private User user;
}
