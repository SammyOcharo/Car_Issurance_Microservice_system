package com.sammydev.reportinginsightservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ReportingInsightServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReportingInsightServiceApplication.class, args);
    }

}
