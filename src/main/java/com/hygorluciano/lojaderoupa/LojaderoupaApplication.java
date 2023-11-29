package com.hygorluciano.lojaderoupa;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class LojaderoupaApplication {

	public static void main(String[] args) {

		SpringApplication.run(LojaderoupaApplication.class, args);
		log.info("Spring boot Start tudo ok ");
	}


}
