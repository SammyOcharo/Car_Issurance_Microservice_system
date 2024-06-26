package com.sammydev.policymanagementservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PolicyManagementServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PolicyManagementServiceApplication.class, args);
    }

}
