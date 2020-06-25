package com.spring.cartracker.awsMessaging;

import com.amazonaws.services.sns.AmazonSNS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.NotificationMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class AlertsSns {

    private final NotificationMessagingTemplate notificationMessagingTemplate;

    @Value("${alerts.topic}")
    private String topic;

    @Autowired
    public AlertsSns(NotificationMessagingTemplate notificationMessagingTemplate) {
        this.notificationMessagingTemplate = notificationMessagingTemplate;
    }

    public void send(String subject, String message) {
        this.notificationMessagingTemplate.sendNotification(topic , message, subject);
    }
}
