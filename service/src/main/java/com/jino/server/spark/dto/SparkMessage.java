package com.jino.server.spark.dto;

import com.jino.common.User;

import java.util.Arrays;
import java.util.Formatter;

public abstract class SparkMessage {

    private static final String userDisplayPattern = "<a href=\"%s\"> %s </a>";

    protected final User updater;

    public SparkMessage(User updater) {
        this.updater = updater;
    }

    public User getUpdater() {
        return updater;
    }

    public String getUpdaterDisplay() {
        return userDisplayFormat(updater);
    }

    public abstract String getSparkMessage();

    public String buildMessage(String... messages) {
        StringBuilder sb = new StringBuilder();
        Arrays.asList(messages).stream().forEach(message -> {
            sb.append(message);
        });

        return sb.toString();
    }

    protected static final String userDisplayFormat(User user) {
        String displayName = user.getDisplayName() == null ? user.getEmailAddress() : user.getDisplayName();
        return new Formatter().format(userDisplayPattern, user.getSelf(), displayName).toString();
    }
}
