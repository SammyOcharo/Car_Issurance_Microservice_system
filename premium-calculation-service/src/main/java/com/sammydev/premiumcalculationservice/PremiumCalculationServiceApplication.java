package com.sammydev.premiumcalculationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PremiumCalculationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PremiumCalculationServiceApplication.class, args);
    }

}
