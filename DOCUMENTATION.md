Documentação - Solução técnica
============

> Essa API possui endpoints para criar usuário e efetuar transferências entre os usuários.

Conteúdo
===========

1. [Stack utilizada](#stack-utilizada)
2. [Arquitetura](#arquitetura)
3. [Endpoints](#endpoints)

Stack utilizada
===========

- Java
- Spring Boot
- JPA Hibernate
- H2Database

Arquitetura
===========

- A aplicação foi segmentada entre os domínios de users e transactions para isolar as regras de negócio.
- A arquitetura escolhida foi a , dividindo a implementação entre controller, service, repository.

Endpoints
===========

### Erros HTTP

|          |                        Description                         |
|:--------:|:----------------------------------------------------------:|
| HTTP 4xx | Código de retorno para erros no cliente. |
| HTTP 5xx |  Código de retorno erros no servidor.  |

<br>

### Endpoints de usuário

#### [POST] - /api/users

Registra um novo usuário na API

|                     |   Nome    |              Descriçao               | Obrigatorio | Único |
|:-------------------:|:---------:|:------------------------------------:|:-----------:|:------:|
| Corpo da requisição | firstName |                Nome do usuário       |     sim     | não |
| Corpo da requisição | lastName  |           Sobrenome do usuário       |     sim     | não |
| Corpo da requisição | document  |        CPF ou CNPJ do usuário        |     sim     | sim |
| Corpo da requisição |   email   |           Email do usuário           |     sim     | sim |
| Corpo da requisição | password  |           Senha do usuário           |     sim     | não |
| Corpo da requisição | balance  |           Valor inicial           |     não     | não |
| Corpo da requisição | userType  | COMMON ou MERCHANT - tipo do usuario |     sim     | não |

#### Corpo de exemplo

```json
{
    "firstName": "User Name",
    "lastName": "Sir name",
    "document": "000.000.000-00",
    "email": "user@email.com",
    "password": "password123",
    "balance": "100.00",
    "userType": "COMMON"
}
```

#### Resposta - **HTTP 201 (CREATED)**

Location header:
```
/api/users/{user_id}
```

<br>

### Endpoints de transferência

#### [POST] - /api/transactions

Envia uma transferência à outro usuário

|                     |     Nome      |               Descriçao                | Obrigatorio |
|:-------------------:|:-------------:|:--------------------------------------:|:-----------:|
| Corpo da requisição |   value   |    Valor da transferência (Número decimal) |     sim     |
| Corpo da requisição |     senderId     | Id do usuario remetente |     sim     |
| Corpo da requisição |  receiverId  |  Id do usuario destino  |     sim     |

#### Corpo de exemplo

```json
{
    "value": "50.00",
    "senderId": 1,
    "receiverId": 2
}
```

#### Resposta - HTTP 201 (CREATED)
