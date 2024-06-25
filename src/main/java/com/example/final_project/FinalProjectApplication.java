package com.example.final_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
public class FinalProjectApplication {
	public static void main(String[] args) {
		SpringApplication.run(FinalProjectApplication.class, args);
	}

	@Bean
	public DataSource dataSource(DataSourceProperties dataSourceProperties) {
		return DataSourceBuilder
				.create()
				.url(dataSourceProperties.determineUrl())
				.username(dataSourceProperties.determineUsername())
				.password(dataSourceProperties.determinePassword())
				.driverClassName(dataSourceProperties.getDriverClassName())
				.build();
	}
}
