package com.jino.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User extends AbstractJiraObject {

    @SerializedName("emailAddress")
    @Expose
    private String emailAddress;

    @SerializedName("displayName")
    @Expose
    private String displayName;

    @SerializedName("active")
    @Expose
    private boolean active;

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getDisplayName() {
        return displayName;
    }

    public boolean isActive() {
        return active;
    }
}
