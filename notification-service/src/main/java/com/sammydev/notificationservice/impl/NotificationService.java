package com.sammydev.notificationservice.impl;

import com.sammydev.notificationservice.events.UserRegistrationEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @KafkaListener(topics = "user-registration", groupId = "notification-group")
    public void listenUserRegistration(UserRegistrationEvent event) {
        // Logic to send welcome email
        sendWelcomeEmail(event.getEmail(), event.getMessage());
    }

    private void sendWelcomeEmail(String email, String message) {
        // Logic to send email
        System.out.println("Sending welcome email to: " + email);
        System.out.println("Message: " + message);
    }
}
