package com.jino.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Comment extends AbstractJiraObject {

    @SerializedName("author")
    @Expose
    private User author;

    @SerializedName("updateAuthor")
    @Expose
    private User updateAuthor;

    @SerializedName("body")
    @Expose
    private String body;

    public User getAuthor() {
        return author;
    }

    public User getUpdateAuthor() {
        return updateAuthor;
    }

    public String getBody() {
        return body;
    }
}
