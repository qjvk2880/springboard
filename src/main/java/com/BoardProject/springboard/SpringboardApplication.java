package com.BoardProject.springboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.lang.annotation.*;

@SpringBootApplication
@EnableJpaAuditing
public class SpringboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringboardApplication.class, args);
	}

}


