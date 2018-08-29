package com.wei.eduyang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wei.eduyang.mapper")
public class EduYangApplication {

	public static void main(String[] args) {
		SpringApplication.run(EduYangApplication.class, args);
	}
}
