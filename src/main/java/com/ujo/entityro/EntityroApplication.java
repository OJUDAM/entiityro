package com.ujo.entityro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 *  2023-08-23
 *  일단 DB 없이 시작
 * */
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class EntityroApplication {

	public static void main(String[] args) {
		SpringApplication.run(EntityroApplication.class, args);
	}

}
