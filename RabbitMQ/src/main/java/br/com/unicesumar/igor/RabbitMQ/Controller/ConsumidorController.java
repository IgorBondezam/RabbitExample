package br.com.unicesumar.igor.RabbitMQ.Controller;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consumidor")
public class ConsumidorController {

    @Value("{$chava-producao-rabbit}")
    private String chaveProducaoRabbit;

    @GetMapping(value = "/get")
    @SneakyThrows
    public void envio() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(chaveProducaoRabbit, false, false, false, null);
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Fazendo tarefa - '" + message + "'");
        };
        channel.basicConsume(chaveProducaoRabbit, true, deliverCallback, consumerTag -> { });
    }

}
