package com.code.factory.stundetrestapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class StundetRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(StundetRestApiApplication.class, args);
	}

}
