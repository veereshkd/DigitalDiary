package com.DigitalDiary;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class DigitalDiaryApplication {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));// To override the JVM System Date Time
		SpringApplication.run(DigitalDiaryApplication.class, args);
	}

}
