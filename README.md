# HelpDesk API

REST API para gerenciamento de usuários, categorias e chamados de suporte, desenvolvida com Java e Spring Boot.

O projeto foi desenvolvido como uma aplicação prática de backend, aplicando conceitos como arquitetura em camadas, APIs REST, JPA/Hibernate, DTOs, validação de dados, tratamento de exceções, paginação, documentação de APIs e testes automatizados.

## Tecnologias

- Java 21
- Spring Boot 4.1.0
- Spring Web MVC
- Spring Data JPA
- Hibernate
- PostgreSQL
- Jakarta Validation
- Maven
- JUnit 5
- Mockito
- Springdoc OpenAPI
- Swagger UI

## Arquitetura

A aplicação utiliza uma arquitetura em camadas, separando as responsabilidades de cada parte do sistema:

\`\`\`text
Controller
    ↓
DTO
    ↓
Service
    ↓
Repository
    ↓
Entity
    ↓
Database
\`\`\`

A conversão entre entidades e objetos utilizados pela API é realizada através de mappers:

\`\`\`text
Entity
   ↓
Mapper
   ↓
DTO
   ↓
JSON
\`\`\`

Estrutura principal do projeto:

\`\`\`text
src/main/java/com/gustavo/helpdeskapi

├── config
├── controller
├── dto
├── entity
├── exception
├── mapper
├── repository
└── service
\`\`\`

## Funcionalidades

### Usuários

A API permite:

- Criar usuários
- Listar usuários
- Buscar usuário por ID
- Atualizar usuários
- Excluir usuários

Os dados enviados e retornados pela API são controlados através de DTOs. Dessa forma, informações sensíveis presentes na entidade, como senha, não são expostas diretamente nas respostas.

### Categorias

A API permite:

- Criar categorias
- Listar categorias

As categorias podem ser utilizadas na criação e organização dos chamados.

### Chamados

A API permite:

- Criar chamados
- Listar chamados
- Buscar chamado por ID
- Atualizar chamados
- Excluir chamados
- Associar um chamado a um usuário
- Associar um chamado a uma categoria
- Definir o status do chamado
- Definir a prioridade do chamado

Os chamados possuem relacionamento com usuários e categorias através do JPA.

## DTOs

A aplicação utiliza Data Transfer Objects para controlar os dados recebidos e enviados pela API.

Principais DTOs:

\`\`\`text
UserDTO
UserCreateDTO

CategoryDTO
CategoryCreateDTO

TicketDTO
TicketCreateDTO

ErrorResponseDTO
\`\`\`

Os DTOs permitem separar o contrato da API das entidades utilizadas para persistência no banco de dados, além de evitar a exposição desnecessária de informações internas das entidades.

## Mappers

A conversão entre entidades e DTOs é centralizada em classes específicas:

\`\`\`text
UserMapper
CategoryMapper
TicketMapper
\`\`\`

Com isso, Controllers e Services permanecem focados em suas respectivas responsabilidades, sem acumular lógica de conversão de objetos.

## Validação

Os dados recebidos pela API são validados utilizando Jakarta Bean Validation.

Entre as validações utilizadas estão:

\`\`\`java
@NotBlank
@NotNull
\`\`\`

Os Controllers utilizam `@Valid` para executar as validações antes que os dados sejam processados pelo Service.

O fluxo de uma requisição é:

\`\`\`text
HTTP Request
     ↓
CreateDTO
     ↓
@Valid
     ↓
Validation
     ↓
Service
\`\`\`

Quando os dados enviados são inválidos, a API retorna `400 Bad Request` com informações sobre os campos que apresentaram erro.

## Tratamento de exceções

O projeto possui tratamento centralizado de exceções através de:

\`\`\`text
ResourceNotFoundException
GlobalExceptionHandler
ErrorResponseDTO
\`\`\`

Quando um recurso não é encontrado, uma `ResourceNotFoundException` é lançada pelo Service e tratada pelo `GlobalExceptionHandler`.

Exemplo de fluxo:

\`\`\`text
ResourceNotFoundException
          ↓
GlobalExceptionHandler
          ↓
HTTP 404
          ↓
ErrorResponseDTO
\`\`\`

Os erros de validação também são tratados de forma centralizada.

Exemplo de resposta:

\`\`\`json
{
  "status": 400,
  "message": "Erro de validação",
  "timestamp": "...",
  "errors": {
    "name": "Nome é obrigatório"
  }
}
\`\`\`

## Paginação e ordenação

A listagem de chamados utiliza `Pageable`, permitindo controlar a quantidade de registros retornados e a ordenação dos resultados.

Exemplo:

\`\`\`http
GET /api/tickets?page=0&size=10&sort=id,desc
\`\`\`

A resposta contém informações de paginação, como:

- Página atual
- Quantidade de registros por página
- Total de registros
- Total de páginas

Exemplo:

\`\`\`json
{
  "content": [],
  "number": 0,
  "size": 10,
  "totalElements": 10,
  "totalPages": 1
}
\`\`\`

## Banco de dados

A aplicação utiliza PostgreSQL como banco de dados relacional.

O acesso e o mapeamento dos dados são realizados utilizando Spring Data JPA e Hibernate.

As principais entidades são:

\`\`\`text
User
Category
Ticket
\`\`\`

Os relacionamentos principais são:

\`\`\`text
User
  │
  └── Ticket

Category
  │
  └── Ticket
\`\`\`

Um usuário pode possuir vários chamados, enquanto cada chamado está associado a um usuário e a uma categoria.

## Endpoints

### Users

\`\`\`http
POST   /api/users
GET    /api/users
GET    /api/users/{id}
PUT    /api/users/{id}
DELETE /api/users/{id}
\`\`\`

### Categories

\`\`\`http
POST /api/categories
GET  /api/categories
\`\`\`

### Tickets

\`\`\`http
POST   /api/tickets
GET    /api/tickets
GET    /api/tickets/{id}
PUT    /api/tickets/{id}
DELETE /api/tickets/{id}
\`\`\`

## Documentação da API

A API utiliza OpenAPI e Swagger UI para documentação e teste dos endpoints.

Após iniciar a aplicação, a documentação pode ser acessada em:

\`\`\`text
http://localhost:8080/swagger-ui/index.html
\`\`\`

A especificação OpenAPI está disponível em:

\`\`\`text
http://localhost:8080/v3/api-docs
\`\`\`

## Testes

O projeto possui testes automatizados utilizando JUnit 5 e Mockito.

Os testes estão concentrados principalmente na camada de Service, utilizando mocks para isolar as regras de negócio do banco de dados.

Principais cenários testados:

\`\`\`text
UserServiceTest
├── Criação de usuário
├── Busca de usuário
└── Usuário inexistente

TicketServiceTest
├── Criação de chamado
├── Busca de chamado
├── Atualização de chamado
├── Exclusão de chamado
├── Usuário inexistente
├── Categoria inexistente
└── Chamado inexistente

CategoryServiceTest
├── Criação de categoria
└── Listagem de categorias
\`\`\`

O projeto possui atualmente **13 testes automatizados**, cobrindo operações de sucesso, recursos inexistentes, interações com repositories e cenários de exceção.

## Como executar

### Pré-requisitos

- Java 21
- PostgreSQL
- IDE de sua preferência

O projeto possui Maven Wrapper, portanto não é necessário instalar o Maven globalmente.

### 1. Clone o repositório

\`\`\`bash
git clone https://github.com/gustavoconce/helpdesk-api.git
\`\`\`

### 2. Configure o banco de dados

Crie um banco PostgreSQL para a aplicação e configure as credenciais em:

\`\`\`text
src/main/resources/application.properties
\`\`\`

Exemplo:

\`\`\`properties
spring.datasource.url=jdbc:postgresql://localhost:5432/helpdesk
spring.datasource.username=postgres
spring.datasource.password=your_password
\`\`\`

### 3. Execute a aplicação

No Windows:

\`\`\`powershell
.\mvnw.cmd spring-boot:run
\`\`\`

Também é possível executar a classe principal da aplicação diretamente pela IDE.

### 4. Acesse a API

A aplicação estará disponível em:

\`\`\`text
http://localhost:8080
\`\`\`

A documentação pode ser acessada pelo Swagger:

\`\`\`text
http://localhost:8080/swagger-ui/index.html
\`\`\`

## Executando os testes

Para executar todos os testes:

\`\`\`powershell
.\mvnw.cmd test
\`\`\`

## Objetivo do projeto

O objetivo do HelpDesk API é demonstrar conhecimentos práticos de desenvolvimento backend utilizando Java e Spring Boot.

O projeto reúne conceitos utilizados no desenvolvimento de aplicações reais, como:

- Desenvolvimento de APIs REST
- Programação orientada a objetos
- Arquitetura em camadas
- Persistência de dados
- Spring Data JPA
- DTOs
- Mappers
- Validação de dados
- Tratamento de exceções
- Paginação e ordenação
- Documentação de APIs
- Testes automatizados

## Autor

Desenvolvido por **Gustavo Conceição**.

Graduado em Sistemas de Informação pela FIAP, com foco em desenvolvimento backend, APIs e engenharia de software.

GitHub: https://github.com/gustavoconce/helpdesk-api
