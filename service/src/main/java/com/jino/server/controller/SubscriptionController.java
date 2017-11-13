package com.jino.server.controller;


import com.jino.server.service.SparkSubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "s")
public class SubscriptionController {


    @Autowired
    private SparkSubscriptionService sparkSubscriptionService;


    @RequestMapping(method = RequestMethod.POST)
    public String subscribe(@RequestBody String email) {
        return sparkSubscriptionService.subscribe(email);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public String unSubscribe(@RequestBody String email) {
        return sparkSubscriptionService.unSubscribe(email);
    }

}
