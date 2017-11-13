package com.jino.server.spark.dto;

import com.jino.common.User;

import java.util.Formatter;

public class IssueCommentUpdateMessage extends IssueMessage {

    private static final String commentUpdatedPattern = "<br/> " +
            "Event : <b> %s updated comment. </b>" +
            "<br/> %s </br>";

    protected final String comment;

    public IssueCommentUpdateMessage(String issueUrl, String issueId, String issueSummary, User user, String comment) {
        super(issueUrl, issueId, issueSummary, user);
        this.comment = comment;
    }


    @Override
    public String getSparkMessage() {
        return super.getSparkMessage() + new Formatter().format(commentUpdatedPattern, getUpdaterDisplay(), comment);

    }


    public static class IssueCommentUpdateMessageBuilder extends IssueMessage.IssueMessageBuilder {

        private String comment;

        public IssueCommentUpdateMessageBuilder comment(String comment) {
            this.comment = comment;
            return this;
        }

        public IssueCommentUpdateMessage build() {
            return new IssueCommentUpdateMessage(issueUrl, issueId, issueSummary, user, comment);
        }

    }
}
