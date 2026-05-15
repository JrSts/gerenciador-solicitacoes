# SGS — Sistema de Gerenciamento de Solicitações

Sistema fullstack para gerenciamento de solicitações, desenvolvido com:

- Backend em Java + Spring Boot
- Frontend em Next.js + React
- Banco PostgreSQL
- Docker e Docker Compose

---

## Tecnologias Utilizadas

### Backend

- Java 17
- Spring Boot
- Spring Web
- Spring JDBC
- PostgreSQL
- Docker

### Frontend

- Next.js 16
- React 19
- TypeScript
- CSS Modules
- Axios

### Banco de Dados

- PostgreSQL

---

## Estrutura do Projeto

```text
gerenciador-solicitacoes/
├── backend/
├── frontend/
└── docker-compose.yml
```

---

## Scripts SQL

O script de criação das tabelas e inserts está localizado em:

```
	backend/src/main/java/com/jrsts/sgs/scripts/initdb.sql
```

### O script contém:

- Criação das tabelas
- Chaves primárias
- Chaves estrangeiras
- Inserts iniciais

## Como Executar o Projeto

### Pré-requisitos

- Docker
- Docker Compose
- Criar arquivo .env no frontend

```
	cp .env.example .env
```

### Executando com Docker

Na raiz do projeto execute:

```
	docker compose up --build
```

## Serviços Disponíveis

#### Frontend

```
	http://localhost:3000
```

### Backend

```
	http://localhost:8080
```

### PostgreSQL

```
	localhost:5432
```

### Banco:

```
	sgs
```

## Variáveis de Ambiente

### Backend

application.properties

```
	SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5432/sgs
	SPRING_DATASOURCE_USERNAME=postgres
	SPRING_DATASOURCE_PASSWORD=postgres
```

### Frontend

.env

```
	NEXT_PUBLIC_API_URL=http://localhost:8080
```

## Endpoints Principais

### Buscar solicitações

```
	GET /solicitacoes
```

### Buscar solicitações com filtros

```
	GET /solicitacoes/filter
```

#### Query params:

- categoriaId
- status
- dataInicio
- dataFim

#### Exemplo

```
	GET /solicitacoes/filter?status=SOLICITADO
```

### Criar solicitação

```
	POST /solicitacoes

{
		"solicitanteId" : "",
		"categoriaId" : "",
		"descricao" : "",
		"valor" : 0
}
```

### Atualizar status

```
	PUT /solicitacoes/{id}

{
		"status" : "APROVADO"
}
```
