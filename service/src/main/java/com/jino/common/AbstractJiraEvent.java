package com.jino.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public abstract class AbstractJiraEvent {

    @SerializedName("timestamp")
    @Expose
    private long timestamp;

    @SerializedName("webhookEvent")
    @Expose
    private String webhookEvent;

    public long getTimestamp() {
        return timestamp;
    }

    public String getWebhookEvent() {
        return webhookEvent;
    }
}
