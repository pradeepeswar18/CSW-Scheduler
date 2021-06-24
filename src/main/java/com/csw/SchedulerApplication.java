package com.csw;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SchedulerApplication implements CommandLineRunner{
	public static void main(String[] args) {
		SpringApplication.run(SchedulerApplication.class, args);
	}
	
	
	@Override
	public void run(String... args) throws Exception {
	}
}
