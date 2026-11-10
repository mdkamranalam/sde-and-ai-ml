package com.mycode.hellospringboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class HellospringbootApplication implements CommandLineRunner {
	@Autowired
	Map<String, NotificationService> notificationServiceMap = new HashMap<>();

	public static void main(String[] args) {

		SpringApplication.run(HellospringbootApplication.class, args);
	}

	@Override
	public void run(String... args) {
//		notificationService.send("Stream is LIVE!");
		for (var notificationService : notificationServiceMap.entrySet()) {
			notificationService.getValue().send("LIVE on YouTube!");
		}
	}
}
