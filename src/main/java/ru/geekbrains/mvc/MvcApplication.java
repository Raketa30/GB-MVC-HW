package ru.geekbrains.mvc;

import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MvcApplication {
	public static void main(String[] args) {
		SpringApplication.run(MvcApplication.class, args);
		Flyway flyway = Flyway.configure()
				.dataSource("jdbc:postgresql://localhost:5432/simple-app", "postgres", "postgrespass").load();
		flyway.migrate();
	}
}
