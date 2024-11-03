package br.com.unicesumar.igor.RabbitMQ.Controller;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produtor")
public class ProdutorController {

    @Value("{$chava-producao-rabbit}")
    private String chaveProducaoRabbit;

    @PostMapping
    @SneakyThrows
    public void envio(@RequestBody String tarefa) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
            Channel channel = connection.createChannel()) {
            channel.queueDeclare(chaveProducaoRabbit, false, false, false, null);
            channel.basicPublish("", chaveProducaoRabbit, null, tarefa.getBytes());
            System.out.println("Tarefa - '" + tarefa + "'");
        }
    }

}
