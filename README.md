# Baozi Store API

API REST desenvolvida como projeto acadêmico da Uninter para praticar desenvolvimento back-end com **Java, Spring Boot e persistência de dados**.

A aplicação organiza três recursos — **clientes, produtos e pedidos** — e permite cadastrar, consultar, listar e excluir registros por endpoints HTTP.

## Tecnologias

- Java 25
- Spring Boot 4.0.8
- Spring Web MVC
- Spring Data JPA / Hibernate
- Banco de dados H2 (arquivo local)
- Maven

## Funcionalidades implementadas

| Recurso | Operações disponíveis |
| --- | --- |
| Clientes | Cadastro, listagem, consulta por ID e exclusão |
| Produtos | Cadastro, listagem, consulta por ID e exclusão |
| Pedidos | Cadastro, listagem, consulta por ID e exclusão |

No cadastro de pedidos, a API verifica se o cliente e o produto informados existem e se a quantidade é maior que zero. Respostas utilizam códigos HTTP como `201 Created`, `400 Bad Request`, `404 Not Found` e `204 No Content`, conforme a operação.

## Endpoints

| Método | Caminho | Descrição |
| --- | --- | --- |
| `POST` | `/clientes` | Cadastra um cliente |
| `GET` | `/clientes` | Lista os clientes |
| `GET` | `/clientes/{id}` | Consulta um cliente |
| `DELETE` | `/clientes/{id}` | Exclui um cliente |
| `POST` | `/produtos` | Cadastra um produto |
| `GET` | `/produtos` | Lista os produtos |
| `GET` | `/produtos/{id}` | Consulta um produto |
| `DELETE` | `/produtos/{id}` | Exclui um produto |
| `POST` | `/pedidos` | Cadastra um pedido |
| `GET` | `/pedidos` | Lista os pedidos |
| `GET` | `/pedidos/{id}` | Consulta um pedido |
| `DELETE` | `/pedidos/{id}` | Exclui um pedido |

### Exemplo de cadastro de pedido

Após cadastrar um cliente e um produto, envie uma requisição `POST /pedidos` com um corpo JSON semelhante a este:

```json
{
  "clienteId": 1,
  "produtoId": 1,
  "quantidade": 2
}
```

A API valida os dados antes de gravar o pedido.

## Como executar localmente

**Pré-requisitos:** JDK 25 e acesso às dependências Maven.

1. Clone o repositório:

   ```bash
   git clone https://github.com/thiagominacio/baozi-store-api.git
   cd baozi-store-api
   ```

2. No Windows, execute:

   ```powershell
   .\mvnw.cmd spring-boot:run
   ```

   Em Linux/macOS, utilize `./mvnw spring-boot:run`.

3. Consulte os endpoints por um cliente HTTP como Postman, Insomnia ou `curl`, usando `http://localhost:8080` como endereço-base, salvo alteração da porta local.

O banco H2 é configurado para persistir os dados em arquivo local, na pasta `data/`, que está excluída do versionamento pelo `.gitignore`.

> **Observação de segurança:** este é um projeto educacional para execução local. A API não implementa autenticação ou autorização. Antes de expor qualquer aplicação na internet, é necessário revisar controle de acesso, validações, configuração e proteção do banco.

## Organização do código

- `controller/`: endpoints REST
- `model/`: entidades persistidas
- `repository/`: acesso a dados com Spring Data JPA
- `src/main/resources/`: configuração da aplicação
- `src/test/`: teste de inicialização do contexto Spring

## Contexto

Projeto acadêmico desenvolvido para exercitar organização em camadas, operações HTTP, persistência relacional e validações básicas de regras de negócio.

**Outros projetos:** [Portfólio de Thiago Moura Inácio](https://thiagominacio.github.io/)
