package com.city.hcy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan({"com.city.hcy.mapper"})
@SpringBootApplication
public class HcyApplication {

	public static void main(String[] args) {
		SpringApplication.run(HcyApplication.class, args);
	}

}
