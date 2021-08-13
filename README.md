# PasswordValidator

Validador de senhas utilizando a tecnologia Spring WebFlux.

![technology java](https://img.shields.io/badge/technology-Java-blue.svg)
![technology Gradle](https://img.shields.io/badge/technology-Gradle-blue.svg)
<a href="https://awesomestacks.dev/res-tful-api-with-java-and-spring-boot"><img src="https://awesome.re/badge-flat2.svg"></a>

## Installation

- Pre-requesitos para iniciar o projeto
    - [**Java 11**](https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html)
    - [**Gradle**](https://docs.gradle.org/current/userguide/userguide.html) | _or use the wrapper ./gradlew_
    - [**Spring Boot 2**](https://spring.io/projects/spring-boot)
    - [**Docker**](https://docs.docker.com/docker-for-mac/install/#download-docker-for-mac)

[**Spring Webflux**](https://docs.spring.io/spring-framework/docs/current/reference/html/web-reactive.html) é uma
tecnologia que traz a programação reativa (non-blocking), podendo trabalhar com assincronidade e melhor tempo de
resposta nas chamadas.

## Description

Pensando na extensibilidade, facilidade nos testes e legibilidade do código, foi implementado o design pattern [**Chain
of Responsability**](https://refactoring.guru/design-patterns/chain-of-responsibility)
onde cada criterio de validação foi implementado por uma classe, podendo assim ser facilmente trocado de posição cada um
dos criterios inseridos ( apesar de cada validação nao interromper a continuidade das proximas), alem de isolar cada
validacao.

Para a validação em si, foi utilizado [**REGEX**](https://regexr.com/), porem o código permite utilizar outras
validações sem alterar o codigo existente, utilizando-se do principio de Interfaces

A chamada é realizada pela URI ```/password/check```, pensando num contexto de microserviços onde uma infraestrutura
contenha um load-balancer unico, facilitando na identificação de cada componente

Para o retorno da validação, optei por duas formas:

- Caso os criterios de aceite sejam cumpridos, é retornado um booleano com o status OK:

  ````
    {
      "passwordCheck": true
    }
  ````

- Caso um ou mais criterios falhem, é retornado uma lista do que precisa ser ajustado, com o status Bad_Request

  ````
    {
      "error":"INVALID_PASSWORD",
      "error_description": [/*lista de erros*/]
    }
  ````
    - Dessa forma, o usuario sabe o que ajustar de uma vez, ao contrario do que seria se em cada validação falha fosse
      interrompido o fluxo

### Installing dependencies

Execute o comando abaixo na raiz do projeto para instalar as dependências:

````
./gradlew clean build
````

### Running the tests

Execute o comando abaixo para executar os testes da aplicação

```
./gradlew clean test
```

## Docker Run

Se prefere utilizar a aplicação no contexto do docker, siga essas intruções:

Para gerar a imagem, utilize:

````
./gradlew docker
````

Para executar, utilize:

````
./gradlew dockerRun
````

A aplicação subirá na porta 8080

## Documentação no swagger

 ````
http://localhost:8080/swagger-ui.html
````