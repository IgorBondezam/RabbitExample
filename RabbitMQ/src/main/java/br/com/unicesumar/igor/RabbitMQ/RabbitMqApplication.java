package br.com.unicesumar.igor.RabbitMQ;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.sql.Array;

@SpringBootApplication
public class RabbitMqApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(RabbitMqApplication.class, args);
	}
}
