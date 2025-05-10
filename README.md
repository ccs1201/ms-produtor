# MS-Produtor - Projeto de Testes CCS-Dispatcher

## Sobre o Projeto
Este é um projeto de microsserviço desenvolvido para testar e demonstrar as funcionalidades do `ccs-dispatcher-starter`. O projeto serve como uma implementação de referência e ambiente de testes para validar o funcionamento do dispatcher de mensagens.

## Tecnologias Utilizadas
- Java 21
- Spring Boot 3.4.5
- RabbitMQ 3.13.6
- CCS-Dispatcher-Starter 1.0.0
- Docker/Docker Compose

## Pré-requisitos
- JDK 21
- Maven
- Docker e Docker Compose
- RabbitMQ (fornecido via Docker Compose)

## Configuração do Ambiente

### 1. Clonar o Repositório
```bash
git clone [url-do-repositorio]
cd ms-produtor
```

### 2. Iniciar o RabbitMQ
```bash
docker-compose up -d
```

O RabbitMQ estará disponível em:

- Management UI: http://localhost:15672
- Credenciais padrão: guest/guest

### 3. Compilar o Projeto
```bash
mvn clean install
```

## Endpoints Disponíveis

### POST /
Envia uma mensagem para processamento assíncrono.
```json
{
  "nome": "string",
  "idade": 0,
  "sexo": "char",
  "dataNascimento": "yyyy-MM-dd",
  "path": "string"
}
```

### POST /consome
Endpoint para consumo de mensagens.
```json
{
  "nome": "string",
  "idade": 0,
  "sexo": "char",
  "dataNascimento": "yyyy-MM-dd",
  "path": "string"
}
```

### POST /throwErro
Endpoint para testar o comportamento de erros.
```json
{
  "nome": "string",
  "idade": 0,
  "sexo": "char",
  "dataNascimento": "yyyy-MM-dd",
  "path": "string"
}
```

## Casos de Teste Implementados
- Envio básico de mensagens
- Consumo de mensagens
- Tratamento de erros e retentativas
- Dead Letter Queue (DLQ)

## Estrutura do Projeto
```
ms-produtor/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── br/com/ccs/msprodutor/
│   │   │       ├── controller/
│   │   │       ├── model/
│   │   │       └── MsProdutorApplication.java
│   │   └── resources/
│   │       └── application.properties
├── docker-compose.yml
├── pom.xml
└── README.md
```

## Como Executar

Certifique-se de que o RabbitMQ está em execução:
```bash
docker-compose ps
```

Execute a aplicação:
```bash
mvn spring-boot:run
```

## Monitoramento
- RabbitMQ Management: http://localhost:15672
- Logs da aplicação: Disponíveis no console e arquivos de log

## Dependências Principais
```xml
<dependency>
    <groupId>br.com.ccs</groupId>
    <artifactId>ccs-dispatcher-starter</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Contribuição
Este é um projeto de testes e demonstração. Sinta-se à vontade para:
- Reportar bugs
- Sugerir melhorias
- Adicionar novos casos de teste

## Relacionados
- CCS-Dispatcher-Starter
- Documentação completa do CCS-Dispatcher

## Status do Projeto
🚧 Em desenvolvimento - Projeto de testes e validação

## Licença
Este projeto está sob a licença Apache 2.0.