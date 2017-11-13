package com.jino.server.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jino.server.service.ExecutorServiceProxy;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class JiraEventController {

    @Autowired
    protected ExecutorServiceProxy eventExecutorService;

    protected static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

}
