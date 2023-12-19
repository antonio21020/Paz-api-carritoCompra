package tech.cognity.ventas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ApiPedApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiPedApplication.class, args);
	}

}
