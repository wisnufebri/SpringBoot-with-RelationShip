package com.latihan2.latihan2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Latihan2Application {

	public static void main(String[] args) {
		SpringApplication.run(Latihan2Application.class, args);
	}

	@Bean
	public PasswordEncoder passwordEncoder (){
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
}
