# PicPay Simplificado

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)

Este repositório contém um projeto Java Spring de um [desafio de código](https://github.com/PicPay/picpay-desafio-backend) do serviço de pagamentos PicPay. O desafio propôs a criação de uma API Restful para realizar transferências de dinheiro entre usuários.

## Tecnologias utilizadas

* [Java](https://www.java.com/)
* [Spring Boot](https://spring.io/projects/spring-boot)
* [H2](https://www.h2database.com/)

O projeto foi baseado no tutorial do canal [Fernanda Kipper | Dev](https://youtu.be/QXunBiLq2SM).

### Payload

POST api/transactions

```json
{
    "value" : 100.00,
    "sender" : 4,
    "receiver" : 15
}
```
