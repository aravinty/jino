package com.jino.server.spark.dto;

import com.jino.common.User;

import java.util.Formatter;


public class IssueAssignMessage extends IssueMessage  {

    private static final String issueAssignedPattern = "<br/> <b> %s assigned %s. </b> <br/>";

    private final User assignedToUser;

    public IssueAssignMessage(String issueUrl, String issueId, String issueSummary, User user, User assignedToUser) {
        super(issueUrl, issueId, issueSummary, user);
        this.assignedToUser = assignedToUser;
    }


    @Override
    public String getSparkMessage() {
        return super.getSparkMessage() + new Formatter().format(issueAssignedPattern, getUpdaterDisplay(), userDisplayFormat(assignedToUser));
    }


    public static class IssueAssignMessageBuilder extends IssueMessage.IssueMessageBuilder {

        private User assignedToUser;

        public IssueAssignMessageBuilder assigned(User assignedToUser) {
            this.assignedToUser = assignedToUser;
            return this;
        }

        public IssueAssignMessage build() {
            return new IssueAssignMessage(issueUrl, issueId, issueSummary, user, assignedToUser);
        }

    }
}
