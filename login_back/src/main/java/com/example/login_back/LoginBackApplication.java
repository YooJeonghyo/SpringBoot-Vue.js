package com.example.login_back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing  //Auditing 사용
@SpringBootApplication
public class LoginBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginBackApplication.class, args);
	}

}
