package com.engenharia_software_moderna;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class CryptographyApplication {

	public static void main(String[] args) {
		SpringApplication.run(CryptographyApplication.class, args);
	}

}
