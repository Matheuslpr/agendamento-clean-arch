# Agendamento Clean Arch API — Java + Spring Boot

API REST para gerenciamento de agendamentos, construída com Java 17 e Spring Boot.

## Sumário

*   [Visão geral](#visão-geral)
*   [Tecnologias e arquitetura](#tecnologias-e-arquitetura)
*   [Recursos implementados](#recursos-implementados)
*   [Estrutura do projeto](#estrutura-do-projeto)
*   [Como rodar localmente (passo a passo)](#como-rodar-localmente-passo-a-passo)
*   [Variáveis de ambiente e configuração](#Configurar-variáveis-de-ambiente)
*   [Endpoints ](#endpoints)
*   [Modelagem e decisões de design](#modelagem-e-decisões-de-design)
*   [Como avaliar o projeto](#como-avaliar-o-projeto)
  

## Visão geral

Este projeto implementa uma API REST para gerenciamento de agendamentos, demonstrando a aplicação da Clean Architecture em um contexto de desenvolvimento Java com Spring Boot. O objetivo é fornecer uma solução robusta e de fácil manutenção para agendamentos, com foco em separação de responsabilidades, testabilidade e escalabilidade. A API permite criar, consultar, atualizar e cancelar agendamentos.

## Tecnologias e arquitetura

### Principais tecnologias
*   Java 17
*   Spring Boot 3.x
*   Spring Data JPA / Hibernate
*   PostgreSQL
*   Flyway
*   Maven
*   Lombok
*   Swagger/OpenAPI

  
### Arquitetura

O projeto segue os princípios da **Clean Architecture**, com uma clara separação de camadas:

*   **Core (Domínio)**: Contém as entidades de negócio, interfaces de gateway e use case.
    *   `Entities`: Representa os objetos de negócio.
    *   `Enums`: Enumerações relacionadas ao domínio.
    *   `Gateway`: Interfaces que definem contratos para acesso a dados ou serviços externos.
    *   `Usecase`: Implementa as regras de negócio e orquestra as operações.
*   **Infrastructure (Infraestrutura)**: Contém as implementações das interfaces de gateway, adaptadores para frameworks e tecnologias externas.
    *   `Beans`: Configurações de beans Spring.
    *   `DTO`: Objetos de Transferência de Dados para comunicação entre camadas e com o exterior.
    *   `Gateway`: Implementações concretas das interfaces de gateway do Core.
    *   `Mapper`: Classes para mapeamento entre entidades de domínio e DTOs.
    *   `Persistence`: Implementações de repositórios usando Spring Data JPA.
    *   `Presentation`: Controladores REST que expõem a API.

## Recursos implementados

* Gerenciamento de Agendamentos: criação, consulta, atualização e cancelamento de agendamentos.
* DTOs para entrada/saída (camada de apresentação desacoplada da entidade)
* Mapeadores (mapeadores) para converter entidades ↔ DTOs
* Documentação Com Swagger/OpenAPI para fácil exploração dos endpoints.

## Estrutura do projeto

```
src/
├── main/
│   ├── java/dev/matheus/agendamento_clear/
│   │   ├── Core/           # Camada de domínio (Entidades, Enums, Gateways, Use Case)
│   │   │   ├── Entities/
│   │   │   ├── Enums/
│   │   │   ├── Gateway/
│   │   │   └── Usecase/
│   │   ├── Infrastructure/ # Camada de infraestrutura (Implementações de Gateways, DTOs, Controllers)
│   │   │   ├── Beans/
│   │   │   ├── DTO/
│   │   │   ├── Gateway/
│   │   │   ├── Mapper/
│   │   │   ├── Persistence/
│   │   │   └── Presentation/
│   │   └── AgendamentoClearApplication.java # Classe principal da aplicação Spring Boot
│   └── resources/
│       ├── db/migration/   # Scripts de migração do Flyway
│       └── application.properties # Configurações da aplicação
└── test/                
```

## Como rodar localmente (passo a passo)

### Pré-requisitos

*   JDK 17+
*   Maven 3.6+
*   PostgreSQL


### Clone o repositório

```bash
git clone https://github.com/Matheuslpr/agendamento-clean-arch.git
cd agendamento-clean-arch
```

### Configurar variáveis de ambiente

Crie um arquivo `application.properties` (ou use o existente) em `src/main/resources` e configure as propriedades essenciais para o banco de dados. Exemplo:

```properties
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

# POSTGRESQL
# ==========================
spring.datasource.url=jdbc:postgresql://localhost:5432/db_agendamento
spring.datasource.username=postgres
spring.datasource.password=0000
spring.datasource.driver-class-name=org.postgresql.Driver

# JPA / HIBERNATE
# ==========================
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.generate-ddl=true

```

### Rodar com Maven 

```bash
mvn clean install
mvn spring-boot:run
```

A aplicação estará disponível em: `http://localhost:8080`

## Endpoints 

*   `POST /agendamentos` — Cria um novo agendamento.
*   `GET /agendamentos` — Lista todos os agendamentos.
*   `GET /agendamentos/{id}` — Busca um agendamento por ID.
*   `PUT /agendamentos/{id}` — Atualiza um agendamento existente.
*   `PUT /agendamentos/{id}/cancelar` — Cancela um agendamento.
*   `PUT /agendamentos/{id}/concluir` — Conclui um agendamento.

## Exemplo de uso 

### Criar agendamento 

**Endpoint:** 
POST /agendamentos 

**Request:** 
```
{ 
  "titulo": "Teste de agendamento", 
  "descricao": "teste", 
  "dataInicio": "2026-03-11T10:00:00", 
  "dataFim": "2026-03-15T21:00:00", 
  "usuário": "matheus" 
}
```
**Response:** 
```
{ 
  "id": 1 , 
  "titulo": "Teste de agendamento" , 
  "descricao": "teste" , 
  "dataInicio": "2026-03-11T10:00:00" , 
  "dataFim": "2026-03-15T21:00:00" , 
  "usuário": "matheus" , 
  "status": "AGENDADO" 
}
```

### Documentação interativa

*   Swagger UI: `/swagger-ui/index.html`.

## Modelagem e decisões de design

*   Clean Architecture: Separação clara de responsabilidades entre domínio, aplicação e infraestrutura, facilitando a manutenção e a testabilidade.
*   DTOs: Utilização de Data Transfer Objects para desacoplar a camada de apresentação das entidades de domínio, evitando vazamento de dados e facilitando a evolução da API.
*   Mapeadores: Classes dedicadas para mapeamento entre entidades e DTOs, garantindo um ponto único de conversão.
*   Transações: Uso de transações em operações críticas para garantir a consistência dos dados .
*   Entidades de Domínio: Foco em encapsular a lógica de negócio e garantir a integridade dos dados.

## Como avaliar o projeto

Para uma avaliação rápida do projeto, sugere-se:

1.  **Código Limpo e Legível**: Examine classes nas camadas `Core/Usecase` e `Infrastructure/Presentation` para observar a separação de responsabilidades e a clareza do código.
2.  **Clean Architecture**: Verifique a organização dos pacotes e a comunicação entre as camadas `Core` e `Infrastructure` para entender a aplicação dos princípios da Clean Architecture.
3.  **Mapeamento e DTOs**: Observe as classes em `Infrastructure/DTO` e `Infrastructure/Mapper` para entender como os dados são transferidos e mapeados.
4.  **Banco de Dados**: Consulte `application.properties` e os scripts de migração em `src/main/resources/db/migration` para compreender a estratégia de persistência.
5.  **Fluxo Completo**: Rode o projeto localmente e execute um fluxo básico (ex: criar → consultar → atualizar → cancelar agendamento) para validar o comportamento geral da API.
