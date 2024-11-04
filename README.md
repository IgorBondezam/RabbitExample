
# Projeto de Comunicação com RabbitMQ - Produtor e Consumidor

Este projeto consiste em duas aplicações Spring Boot, **Produtor** e **Consumidor**, que se comunicam através do RabbitMQ. O **Produtor** envia mensagens para uma fila no RabbitMQ, e o **Consumidor** lê as mensagens dessa fila.

## Pré-requisitos

1. **Docker** instalado para execução do RabbitMQ.
2. **Java 17** ou versão superior.
3. **Maven** para build e gerenciamento de dependências.

## Configuração do RabbitMQ

Para configurar o RabbitMQ, execute o seguinte comando no terminal para inicializar o contêiner Docker:

```bash
docker run -d --hostname my-rabbit --name some-rabbit -p 5672:5672 -p 15672:15672 rabbitmq:3-management
```

Isso irá:
- Iniciar um contêiner RabbitMQ.
- Tornar o RabbitMQ acessível na porta `5672` (para comunicação com as aplicações) e `15672` (para acesso à interface de gerenciamento web do RabbitMQ).

Acesse a interface de gerenciamento do RabbitMQ em `http://localhost:15672` com o login padrão (`username`: guest, `password`: guest).

## Endpoints

As aplicações **Produtor** e **Consumidor** possuem os seguintes endpoints:

### 1. Produtor

**Endpoint:** `POST /produtor`  
**URL:** `http://localhost:8081/produtor`  
**Descrição:** Envia uma mensagem para a fila RabbitMQ.  
**Corpo da Requisição:**  
```json
{
  "mensagem": "Sua mensagem aqui"
}
```

### 2. Consumidor

**Endpoint:** `GET /consumidor/get`  
**URL:** `http://localhost:8080/consumidor/get`  
**Descrição:** Lê a próxima mensagem disponível na fila RabbitMQ.

## Executando as Aplicações

1. **Produtor**:
   - No diretório do projeto do **Produtor**, execute:
     ```bash
     mvn spring-boot:run
     ```
   - O serviço será iniciado em `http://localhost:8081`.

2. **Consumidor**:
   - No diretório do projeto do **Consumidor**, execute:
     ```bash
     mvn spring-boot:run
     ```
   - O serviço será iniciado em `http://localhost:8080`.

## Notas

- Certifique-se de que o RabbitMQ está rodando antes de iniciar as aplicações.
- Este projeto utiliza o Spring AMQP para comunicação com o RabbitMQ.
