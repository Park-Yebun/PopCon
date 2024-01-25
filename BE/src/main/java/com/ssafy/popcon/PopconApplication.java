package com.ssafy.popcon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.ssafy"})
public class PopconApplication {

	public static void main(String[] args) {
		SpringApplication.run(PopconApplication.class, args);
	}

}
