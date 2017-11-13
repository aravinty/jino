package com.jino.server.spark;

import com.ciscospark.Spark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.net.URI;

@Configuration
public class SparkConfiguration extends WebMvcConfigurationSupport {


    @Autowired
    private SparkProperties sparkProperties;

    @Bean
    public Spark sparkNotificationSender() {
        return Spark.builder()
                .baseUrl(URI.create(sparkProperties.getCiscoSparkAPI()))
                .accessToken(sparkProperties.getAccessToken())
                .build();
    }


    @Bean
    public JiraEventProcessor jiraEventProcessor() {
        return new JiraEventProcessor();
    }

    @Bean
    public SparkMessageSender sparkMessageSender() {
        return new SparkMessageSender();
    }

}
