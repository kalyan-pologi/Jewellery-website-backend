package com.jewellery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;



@SpringBootApplication
public class JewelleryWebsiteApplication implements CommandLineRunner {


	@Autowired
	private PasswordEncoder passwordEncoder;
	public static void main(String[] args) {
		SpringApplication.run(JewelleryWebsiteApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
//		System.out.println(passwordEncoder.encode("abc"));
	}
}
