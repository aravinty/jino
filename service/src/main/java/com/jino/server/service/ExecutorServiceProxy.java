package com.jino.server.service;

import com.jino.common.IssueEvent;
import com.jino.server.Utils.ThreadPoolExecutorService;
import com.jino.server.spark.JiraEventProcessor;
import com.jino.server.spark.SparkMessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.logging.Logger;


@Service
public class ExecutorServiceProxy {

    Logger logger = Logger.getLogger(ExecutorServiceProxy.class.getName());


    @Autowired
    private ThreadPoolExecutorService executorService;

    @Autowired
    private JiraEventProcessor jiraEventProcessor;


    @Autowired
    private SparkMessageSender messageSender;


    public void execute(final IssueEvent issueEvent) {
        Runnable event = new Runnable() {
            @Override
            public void run() {
                jiraEventProcessor.process(issueEvent);
            }
        };
        executorService.submit(event);
    }


    public void sendMessage(Set<String> emails, String message) {
        if(emails != null && message != null) {
            emails.stream().forEach( email -> {
                executorService.submit(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            messageSender.send(email, message);
                        }catch (ClassCastException e) {
                            logger.severe("ClassCastException");
                            e.printStackTrace();
                        }
                    }
                });
            });
        }
    }
}
