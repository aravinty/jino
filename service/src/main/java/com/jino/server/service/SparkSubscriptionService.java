package com.jino.server.service;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
public class SparkSubscriptionService {

    private Set<String> subscribedSparkEmails = new HashSet<>();

    public String subscribe(String email) {
        subscribedSparkEmails.add(email);
        return email;
    }

    public String unSubscribe(String email) {
        subscribedSparkEmails.remove(email);
        return email;
    }

    public boolean isSubscribed(String email) {
        return subscribedSparkEmails.contains(email);
    }

}
