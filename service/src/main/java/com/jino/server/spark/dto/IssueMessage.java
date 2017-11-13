package com.jino.server.spark.dto;


import com.jino.common.Issue;
import com.jino.common.User;

import java.util.Formatter;

public abstract class IssueMessage extends SparkMessage {


    protected static final String titleTemplate = "<blockquote class=\"info\"> " +
            "<a href=\"%s\"><h4>(#<b>%s</b>) %s </h4></a>" +
            "<br/>" +
            "</blockquote>";

    protected final String issueUrl;

    protected final String issueId;

    protected final String issueSummary;


    public IssueMessage(String issueUrl, String issueId, String issueSummary, User updater) {
        super(updater);
        this.issueUrl = issueUrl;
        this.issueId = issueId;
        this.issueSummary = issueSummary;
    }

    @Override
    public String getSparkMessage() {
        return new Formatter().format(titleTemplate, issueUrl, issueId, issueSummary).toString();
    }

    public abstract static class IssueMessageBuilder<T extends IssueMessage> {

        protected String issueUrl;

        protected String issueId;

        protected String issueSummary;

        protected User user;

        public IssueMessageBuilder issue(Issue issue) {
            this.issueUrl = issue.getSelf();
            this.issueId = issue.getId();
            this.issueSummary = issue.getFields().getSummary();
            return this;
        }

        public IssueMessageBuilder updater(User user) {
            this.user = user;
            return this;
        }

        public abstract T build();

    }
}
