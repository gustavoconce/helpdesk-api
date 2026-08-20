# HelpDesk API

> **Versão atual: V1.0 — Projeto em evolução**

API REST desenvolvida em Java e Spring Boot para gerenciamento de chamados de suporte técnico.

O projeto simula uma aplicação de HelpDesk, permitindo o gerenciamento de usuários, categorias e tickets, além do relacionamento entre essas entidades.

Esta é a **primeira versão do projeto**. A aplicação continuará sendo evoluída em novas versões, incorporando gradualmente novas funcionalidades, melhorias de arquitetura, segurança, testes e infraestrutura.

---

## 🚀 Tecnologias

- Java 17
- Spring Boot
- Spring Data JPA
- Hibernate
- PostgreSQL
- Maven
- Bean Validation
- Postman

---

## 🏗️ Arquitetura

O projeto utiliza uma arquitetura em camadas:

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
PostgreSQL
```

### Controller

Responsável por receber as requisições HTTP e encaminhá-las para a camada de serviço.

### Service

Responsável pela lógica de negócio da aplicação.

### Repository

Responsável pelo acesso e persistência dos dados utilizando Spring Data JPA.

### Entity

Representa as entidades do domínio que são persistidas no banco de dados.

---

## 📌 Funcionalidades da V1

### Usuários

- Criar usuário
- Listar usuários
- Buscar usuário por ID
- Atualizar usuário
- Excluir usuário

### Categorias

- Criar categoria
- Listar categorias

### Tickets

- Criar ticket
- Listar tickets
- Buscar ticket por ID
- Atualizar ticket
- Excluir ticket
- Associar ticket a um usuário
- Associar ticket a uma categoria

### Validação e tratamento de erros

- Validação de dados utilizando Bean Validation
- Validação de campos obrigatórios
- Validação de e-mail
- Tratamento global de exceções
- Respostas HTTP apropriadas para erros
- Tratamento de recursos não encontrados

---

## 🔗 Relacionamentos

Um usuário pode possuir diversos tickets.

Uma categoria pode estar associada a diversos tickets.

```text
User 1 ───────── N Ticket N ───────── 1 Category
```

---

## 📡 Endpoints

### Users

| Método | Endpoint | Descrição |
|---|---|---|
| POST | `/api/users` | Criar usuário |
| GET | `/api/users` | Listar usuários |
| GET | `/api/users/{id}` | Buscar usuário |
| PUT | `/api/users/{id}` | Atualizar usuário |
| DELETE | `/api/users/{id}` | Excluir usuário |

### Categories

| Método | Endpoint | Descrição |
|---|---|---|
| POST | `/api/categories` | Criar categoria |
| GET | `/api/categories` | Listar categorias |

### Tickets

| Método | Endpoint | Descrição |
|---|---|---|
| POST | `/api/tickets` | Criar ticket |
| GET | `/api/tickets` | Listar tickets |
| GET | `/api/tickets/{id}` | Buscar ticket |
| PUT | `/api/tickets/{id}` | Atualizar ticket |
| DELETE | `/api/tickets/{id}` | Excluir ticket |

---

## 🧪 Testes

As requisições da V1 foram testadas manualmente utilizando o Postman.

Foram realizados testes de:

- Criação de usuários
- Consulta de usuários
- Atualização e exclusão de usuários
- Criação e consulta de categorias
- Criação de tickets
- Consulta de tickets
- Atualização e exclusão de tickets
- Relacionamento entre usuários, categorias e tickets
- Validação de dados
- Tratamento de recursos inexistentes

---

## ▶️ Como executar

### Pré-requisitos

- Java 17+
- Maven
- PostgreSQL

Configure a conexão com o banco de dados no arquivo:

```text
src/main/resources/application.properties
```

Depois execute:

```bash
./mvnw spring-boot:run
```

No Windows:

```bash
mvnw.cmd spring-boot:run
```

A API estará disponível em:

```text
http://localhost:8080
```

---

## 📈 Roadmap

O projeto será desenvolvido de forma incremental, com novas versões adicionando funcionalidades e melhorias técnicas.

### ✅ V1.0 — Fundamentos da API

- API REST
- CRUD de usuários
- CRUD de tickets
- CRUD básico de categorias
- PostgreSQL
- Spring Data JPA
- Relacionamentos entre entidades
- Enums
- Bean Validation
- Tratamento global de exceções
- Testes com Postman

### 🔜 V2.0 — Evolução da API

- DTOs
- Paginação
- Filtros
- Melhorias nas respostas da API
- Refinamento da arquitetura

### 🔜 V3.0 — Segurança

- Spring Security
- Autenticação
- JWT
- Autorização baseada em roles

### 🔜 V4.0 — Qualidade e infraestrutura

- Testes unitários
- Testes de integração
- Swagger / OpenAPI
- Docker
- Docker Compose

### 🔜 V5.0 — Aplicação completa

- Desenvolvimento de frontend
- Integração frontend + backend
- Deploy
- Melhorias de experiência do usuário

---

## 📚 Objetivo do projeto

Este projeto está sendo desenvolvido como parte de uma jornada prática de evolução em desenvolvimento backend com Java.

A proposta é construir a aplicação de forma incremental, documentando cada versão e aplicando novos conceitos conforme o projeto evolui.

**Projeto em evolução — novas versões serão adicionadas ao longo do desenvolvimento.**

---

## 👨‍💻 Desenvolvedor

**Gustavo Santos Conceição**
Projeto desenvolvido para estudos, prática de desenvolvimento backend e construção de portfólio.
