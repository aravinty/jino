package com.jino.server.spark.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jino.common.Issue;
import com.jino.common.IssueEvent;
import com.jino.common.User;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

public class IssueEventUsersUtil {

    private static final Logger logger = Logger.getLogger(IssueEventUsersUtil.class.getName());

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static Set<String> getTargetEmails(IssueEvent issueEvent) {
        Set<String> emails = new HashSet<>();
        Issue issue = issueEvent.getIssue();

        logger.info("creator : " + gson.toJson(issue.getFields().getCreator()));
        logger.info("reporter : " + gson.toJson(issue.getFields().getReporter()));
        logger.info("assignee : " + gson.toJson(issue.getFields().getAssignee()));

        //creator
        emails.add(getEmail(issue.getFields().getCreator()));

        //reporter
        emails.add(getEmail(issue.getFields().getReporter()));

        //assignee
        emails.add(getEmail(issue.getFields().getAssignee()));

        //TODO: Add mentions

        //TODO: Add watchers.

        return emails;
    }

    private static String getEmail(User user) {
        if(user == null || !user.isActive()) {
            return null;
        } else {
            return user.getEmailAddress();
        }
    }
}
