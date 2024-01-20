package com.neidev.eventsms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EventsMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventsMsApplication.class, args);
	}

}
