package com.jino.server.controller;

import com.jino.common.IssueEvent;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "issue")
public class IssueController extends JiraEventController {

    @RequestMapping(method = RequestMethod.POST)
    public void postIssue(@RequestBody String issue) {
        IssueEvent ie = gson.fromJson(issue.replaceAll("\n", ""), IssueEvent.class);
        eventExecutorService.execute(ie);
    }
}
