package br.com.unicesumar.igor.RabbitMQ;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProdutorApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ProdutorApplication.class, args);
	}
}
