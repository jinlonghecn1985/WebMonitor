package com.hnjing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SiteHealthMonitorApplication {

	public static void main(String[] args) {
		SpringApplication.run(SiteHealthMonitorApplication.class, args);
	}
}
