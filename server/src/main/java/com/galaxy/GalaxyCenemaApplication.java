package com.galaxy;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;;

@SpringBootApplication
public class GalaxyCenemaApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(GalaxyCenemaApplication.class, args);
		System.out.println("server is running..");
	}

	@Override
	public void run(String... args) throws Exception {

	}

}
