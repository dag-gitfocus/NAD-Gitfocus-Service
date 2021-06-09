package com.gitfocus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tech Mahindra 
 * Initial class to boot GitFocus Application
 */
@RestController
@SpringBootApplication
public class GitFocusApplication {

	private static final Logger logger = LoggerFactory.getLogger(GitFocusApplication.class);

	public GitFocusApplication() {
		super();
		// TODO Auto-generated constructor stub
		logger.info("Starting GitFocus-Service Application..");
	}

	@GetMapping(value = "/hello")
	public String codeVulnerabiltyCheck() {
		return "App is running Successfully";
	}

	public static void main(String[] args) {
		SpringApplication.run(GitFocusApplication.class, args);
	}
}