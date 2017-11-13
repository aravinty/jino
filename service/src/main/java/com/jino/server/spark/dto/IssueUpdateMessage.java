package com.jino.server.spark.dto;

import com.jino.common.User;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

public class IssueUpdateMessage extends IssueMessage {


    private static final String updatePattern = "<br/> <b> %s updated %s<br/>";

    private static final String messagePattern = "%s </b> from <b>\"%s\"</b> to <b>\"%s\"</b>.";


    private final List<String> updates;

    public IssueUpdateMessage(String issueUrl, String issueId, String issueSummary, User updater, List<String> updates) {
        super(issueUrl, issueId, issueSummary, updater);
        this.updates = updates;
    }

    @Override
    public String getSparkMessage() {
        StringBuilder sb = new StringBuilder(super.getSparkMessage());
        updates.stream().forEach(update -> {
            sb.append(new Formatter().format(updatePattern, getUpdaterDisplay(), update));
        });
        return sb.toString();
    }

    public static class IssueUpdateMessageBuilder extends IssueMessage.IssueMessageBuilder {

        private List<String> updates = new ArrayList<>();

        public IssueUpdateMessage.IssueUpdateMessageBuilder updateType(String updateType, String from, String to) {
            updates.add(new Formatter().format(messagePattern,updateType, from, to).toString());
            return this;
        }

        public IssueUpdateMessage build() {
            return new IssueUpdateMessage(issueUrl, issueId, issueSummary, user, updates);
        }

    }
}
