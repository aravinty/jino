package com.jino.server.spark;

import com.ciscospark.Message;
import com.ciscospark.Spark;
import com.jino.server.service.SparkSubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SparkMessageSender {

    Logger logger = Logger.getLogger(SparkMessageSender.class.getName());

    @Autowired
    private Spark spark;

    @Autowired
    private SparkSubscriptionService sparkSubscriptionService;


    public void send(String email, String messageToSend) {

        if(email == null) {
            return;
        }

        if(sparkSubscriptionService.isSubscribed(email)) {
            Message message = new Message();
            message.setMarkdown(messageToSend);
            message.setToPersonEmail(email);
            try {
                logger.info("Message to be sent to User : " + email + " \n \n Message : \n " + messageToSend);
                spark.messages().post(message);
                logger.info("message sent to user " + email + ".");
            }catch (Exception e) {
                logger.log(Level.SEVERE,"Error while sending message to Spark user " + email, e);
            }
        } else {
            logger.info("user " + email + " not subscribed for Jira notification.");
        }
    }
}
