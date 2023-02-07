package com.example.sungjae_notice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SungjaeNoticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SungjaeNoticeApplication.class, args);
	}

}
