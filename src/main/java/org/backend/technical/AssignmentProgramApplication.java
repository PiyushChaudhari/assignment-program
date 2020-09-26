package org.backend.technical;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class AssignmentProgramApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssignmentProgramApplication.class, args);
	}

}
