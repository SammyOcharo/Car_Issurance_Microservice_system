package com.sammydev.dataprocessingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DataProcessingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataProcessingServiceApplication.class, args);
    }

}
