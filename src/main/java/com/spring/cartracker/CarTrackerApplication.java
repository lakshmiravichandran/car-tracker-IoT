package com.spring.cartracker;

import com.spring.cartracker.awsMessaging.AlertsListenerSqs;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class CarTrackerApplication {

	public static void main(String[] args) {

		ApplicationContext applicationContext = SpringApplication.run(CarTrackerApplication.class, args);
		// SQS Listener
		AlertsListenerSqs alertsListenerSqs= applicationContext.getBean(AlertsListenerSqs.class);
		alertsListenerSqs.startListeningToMessages();

	}

}
