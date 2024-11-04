# Explicação Mensageria - Fila

Mensageria Básica

Mensageria é um sistema que permite a comunicação assíncrona entre diferentes sistemas, serviços ou aplicações. Os conceitos fundamentais são:

- Filas: São estruturas de dados que armazenam mensagens temporariamente. Elas seguem a ordem FIFO (First In, First Out), ou seja, a primeira mensagem inserida é a primeira a ser consumida.

- Produtores: Aplicações ou serviços que enviam mensagens para uma fila. Eles geram eventos ou dados que outros sistemas precisam processar.

- Consumidores: Aplicações ou serviços que leem e processam mensagens de uma fila. Eles são responsáveis por realizar alguma ação com os dados recebidos.

- Tópicos: Um tipo especial de fila que permite a publicação de mensagens para múltiplos consumidores interessados em categorias específicas de mensagens. Isso é muito usado em sistemas de broadcast.

- Partições: Em sistemas de mensageria distribuída como o Kafka, as filas podem ser divididas em partições para distribuir a carga entre múltiplos consumidores, melhorando a escalabilidade.

## RabbitMQ

RabbitMQ é um sistema de mensageria que implementa o protocolo AMQP (Advanced Message Queuing Protocol), amplamente utilizado para casos de uso de comunicação em tempo real e para sistemas que necessitam de alta confiabilidade. Seus principais conceitos e funcionalidades incluem:

- Exchanges: Ponto de entrada das mensagens no RabbitMQ. O produtor envia mensagens para uma exchange, que então distribui essas mensagens para as filas com base nas regras de roteamento.

- Tipos de Exchanges:

  - Direct: Envia mensagens para filas com uma chave de roteamento exata.
  - Topic: Usa padrões para combinar chaves de roteamento com filas, permitindo assinaturas mais flexíveis.
  - Fanout: Envia uma cópia de cada mensagem para todas as filas vinculadas, independentemente da chave de roteamento.
  - Headers: Baseia-se nos cabeçalhos das mensagens para roteamento, em vez das chaves de roteamento.
- Bindings: Ligações entre exchanges e filas, que definem como as mensagens devem ser roteadas.

- Confirmação de Mensagens: RabbitMQ suporta confirmações para garantir que as mensagens foram recebidas e processadas corretamente pelo consumidor.

- Redelivery e Dead Letter Queues (DLQ): Mensagens que não são processadas com sucesso podem ser reenviadas para a fila ou direcionadas para uma DLQ para análise posterior.

Caso de Uso Comum do RabbitMQ: RabbitMQ é ideal para casos onde a entrega garantida e a persistência de mensagens são fundamentais, como sistemas de pedidos em e-commerce, notificações, e tarefas de processamento de longa duração.

## Comparação com BullMQ e Kafka

- BullMQ: É uma biblioteca para Node.js que usa Redis como backend para filas. BullMQ é mais leve e simples que o RabbitMQ e Kafka, sendo adequado para filas de trabalho simples e rápidas, sem complexidade de roteamento ou garantia de entrega persistente. É frequentemente utilizado para tarefas curtas, agendamentos e sistemas que não exigem durabilidade de mensagens.

- Kafka: Um sistema de mensageria distribuído projetado para manipular grandes volumes de dados. Kafka utiliza tópicos com partições, permitindo que grandes quantidades de dados sejam processadas em paralelo. Ele é ideal para eventos de streaming e análise em tempo real. Diferente do RabbitMQ, Kafka retém mensagens para que consumidores possam lê-las posteriormente, tornando-o adequado para sistemas de análise de dados e logs.

## Resumo das Diferenças

- RabbitMQ:

  - Protocolo: AMQP
  - Foco: Comunicação assíncrona com roteamento complexo.
  - Garantia de entrega: Alta, com confirmações e DLQs.
  - Casos de uso: Notificações, sistemas de pedidos, processamento de tarefas.
- BullMQ:

  - Backend: Redis
  - Foco: Filas de trabalho simples.
  - Garantia de entrega: Limitada, depende da configuração do Redis.
  - Casos de uso: Tarefas leves e rápidas, agendamentos simples.
- Kafka:

  - Protocolo: Customizado (Kafka API)
  - Foco: Alta escalabilidade, streaming de eventos.
  - Garantia de entrega: Alta, com retenção de mensagens para análise posterior.
  - Casos de uso: Streaming de dados, sistemas de log e análise em tempo real.

---


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
