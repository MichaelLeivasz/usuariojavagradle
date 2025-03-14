# Microsserviço de Usuários

Este microsserviço, desenvolvido em Java com Gradle, gerencia informações de usuários e fornece endpoints para cadastro, autenticação, atualização e exclusão de usuários, além de gerenciar endereços e telefones. Ele utiliza um banco de dados PostgreSQL para persistência e o serviço ViaCEP para busca de endereços.

## Tecnologias Utilizadas

* **Java:** Linguagem de programação principal.
* **Gradle:** Sistema de build automatizado.
* **Spring Boot:** Framework para desenvolvimento rápido de aplicações Java.
* **Spring Security:** Framework para segurança de aplicações.
* **JWT (JSON Web Tokens):** Para autenticação e autorização.
* **PostgreSQL:** Banco de dados relacional.
* **ViaCEP API:** Serviço para busca de endereços a partir de CEPs.

## Pré-requisitos

* Java JDK 17 ou superior.
* Gradle 7 ou superior.
* Docker (opcional, para executar o PostgreSQL em container).
* PostgreSQL instalado e configurado.

## Endpoints da API

### Usuários

* **`POST /usuario`**: Cadastra um novo usuário.
    * Corpo da requisição: `UsuarioDTO` (email, senha, nome, etc.).
    * Retorno: `UsuarioDTO`.
* **`POST /usuario/login`**: Autentica um usuário e retorna um token JWT.
    * Corpo da requisição: `UsuarioDTO` (email, senha).
    * Retorno: `Bearer <token>`.
* **`GET /usuario?email={email}`**: Busca um usuário por email.
    * Retorno: `UsuarioDTO`.
* **`DELETE /usuario/{email}`**: Deleta um usuário por email.
* **`PUT /usuario`**: Atualiza dados de um usuário.
    * Header da requisição: Authorization : Bearer {token}
    * Corpo da requisição: `UsuarioDTO`
    * Retorno: `UsuarioDTO`

### Endereços

* **`POST /usuario/endereco`**: Cadastra um endereço para um usuário.
    * Header da requisição: Authorization : Bearer {token}
    * Corpo da requisição: `EnderecoDTO`
    * Retorno: `EnderecoDTO`
* **`PUT /usuario/endereco?id={id}`**: Atualiza um endereço.
    * Corpo da requisição: `EnderecoDTO`
    * Retorno: `EnderecoDTO`
* **`GET /usuario/endereco/{cep}`**: Busca dados de endereço por CEP utilizando ViaCEP.
    * Retorno: `ViaCepDTO`.

### Telefones

* **`POST /usuario/telefone`**: Cadastra um telefone para um usuário.
    * Header da requisição: Authorization : Bearer {token}
    * Corpo da requisição: `TelefoneDTO`
    * Retorno: `TelefoneDTO`
* **`PUT /usuario/telefone?id={id}`**: Atualiza um telefone.
    * Corpo da requisição: `TelefoneDTO`
    * Retorno: `TelefoneDTO`

## Segurança

* A API utiliza JWT para autenticação e autorização.
* Os endpoints protegidos requerem um token JWT válido no cabeçalho `Authorization`.
