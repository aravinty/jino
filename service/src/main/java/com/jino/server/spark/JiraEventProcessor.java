package com.jino.server.spark;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jino.common.IssueEvent;
import com.jino.common.User;
import com.jino.server.service.SparkSubscriptionService;
import com.jino.server.service.ExecutorServiceProxy;
import com.jino.server.spark.dto.*;
import com.jino.server.spark.utils.IssueEventUsersUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.logging.Logger;

public class JiraEventProcessor  {

    Logger logger = Logger.getLogger(JiraEventProcessor.class.getName());

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Autowired
    private ExecutorServiceProxy threadedExecutorService;

    @Autowired
    private SparkSubscriptionService sparkSubscriptionService;


    public void process(IssueEvent issueEvent) {

        String message = null;

        logger.info("issueEvent.getWebhookEvent() -> "+ issueEvent.getWebhookEvent());

        switch (issueEvent.getWebhookEvent()) {
            case "jira:issue_created":
                logger.info("Issue created event : \n " + gson.toJson(issueEvent) + "\n\n");
                break;
            case "jira:issue_updated":
                message = processIssueUpdateEvent(issueEvent);
                break;
            case "jira:issue_deleted":
                logger.info("Issue deleted event : \n " + gson.toJson(issueEvent)  + "\n\n");
                break;
            default:
                logger.warning("webhook event not handled. \n" + gson.toJson(issueEvent)  +" \n\n ");
        }

        if(message != null) {
            threadedExecutorService.sendMessage(IssueEventUsersUtil.getTargetEmails(issueEvent), message);
        }
    }



    private String processIssueUpdateEvent(IssueEvent issueEvent) {

        String sparkMessage = null;
        switch (issueEvent.getIssueEventTypeName()) {
            case "issue_commented":
                IssueCommentUpdateMessage.IssueCommentUpdateMessageBuilder commentBuilder = new IssueCommentUpdateMessage.IssueCommentUpdateMessageBuilder();
                commentBuilder.issue(issueEvent.getIssue());
                User commentAuthor = issueEvent.getComment().getUpdateAuthor();
                if(commentAuthor == null) {
                    commentAuthor = issueEvent.getComment().getAuthor();
                }
                commentBuilder.updater(commentAuthor);
                commentBuilder.comment(issueEvent.getComment().getBody());
                sparkMessage = commentBuilder.build().getSparkMessage();
                break;
            case "issue_generic":
            case "issue_assigned":
            case "issue_updated":
                IssueUpdateMessage.IssueUpdateMessageBuilder updateBuilder = new IssueUpdateMessage.IssueUpdateMessageBuilder();
                updateBuilder.issue(issueEvent.getIssue());
                updateBuilder.updater(issueEvent.getUser());
                issueEvent.getChangelog().getItems().stream().forEach( item -> {
                    updateBuilder.updateType(item.getFieldId(),item.getFromString(), item.getToString());
                });
                sparkMessage = updateBuilder.build().getSparkMessage();
                break;
            default:
                logger.warning("Issue update event " + issueEvent.getIssueEventTypeName() + " not handled.");
        }

        return sparkMessage;
    }

}
