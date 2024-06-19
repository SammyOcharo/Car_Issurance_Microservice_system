package com.sammydev.billingpaymentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BillingPaymentServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingPaymentServiceApplication.class, args);
    }

}
