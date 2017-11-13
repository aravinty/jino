package com.jino.server.spark;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class SparkProperties {

    private static final String default_ciscoSparkAPI = "defaultAPI";

    private static final String default_accessToken = "defaultToken";


    @Autowired
    private Environment environment;

    public String getCiscoSparkAPI() {
        System.out.println(environment.getProperty("ciscoSparkAPI", default_ciscoSparkAPI));
        return environment.getProperty("ciscoSparkAPI", default_ciscoSparkAPI);
    }

    public String getAccessToken() {
        System.out.println(environment.getProperty("accessToken", default_accessToken));
        return environment.getProperty("accessToken", default_accessToken);
    }
}
