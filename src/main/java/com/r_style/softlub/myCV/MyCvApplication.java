package com.r_style.softlub.myCV;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MyCvApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyCvApplication.class, args);
	}

}
