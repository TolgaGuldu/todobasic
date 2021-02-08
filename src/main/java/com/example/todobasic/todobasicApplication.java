package com.example.todobasic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created on February, 2021
 *
 * @author tolga
 */

@SpringBootApplication
public class todobasicApplication {

	public static void main(String[] args) {
		SpringApplication.run(todobasicApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
