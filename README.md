# Gerenciador de Questões com Spring MVC
## WIP
Webapp para gerenciar as questões dos meus apps de simulados de concursos engenharia.

## Como rodar a aplicação localmente:
- Baixe o projeto com git clone.
- Configure o Banco de Dados em *src/main/resources/application.properties*
- Caso não tenho o MySQL instalado. Pode utilizar o H2 como BD provisório (Os dados 
somem quando fecha a aplicação). 

Para isso copie e cole as configurações abaixo dentro do application.properties:

spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://localhost:3306/{*nome da tabela*}
spring.datasource.username={*nome do usuário no bd*}
spring.datasource.password={*password no bd*}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
#spring.jpa.show-sql: true

- Abra o projeto com seu editor favorito.
- Vá até o arquivo *src/main/java/br/com/engrenantorres/questionmanager/QuestionmanagerApplication.java*
- Aperte o botão verde de play;
- Para conseguir se logar, será preciso criar tabelas de usuários no padrão do spring Security: 
- Para isso: acesse *localhost:8080/h2-console* coloque os mesmos dados que vc inseriu no arquivo application.properties.
- Se conecte ao BD e rode o script abaixo para criar as tabelas padrões:

*USE simulador_concursos;
CREATE TABLE users (
username VARCHAR(50) NOT NULL,
password VARCHAR(200) NOT NULL,
enabled TINYINT NOT NULL DEFAULT 1,
PRIMARY KEY (username));
CREATE TABLE authorities (
username VARCHAR(50) NOT NULL,
authority VARCHAR(50) NOT NULL,
FOREIGN KEY (username) REFERENCES users(username));*

CREATE UNIQUE INDEX ix_auth_username
on authorities (username,authority);

- Para criar o primeiro usuário *admin*:
  basta descomentar o código /* *jdbcUserDetailsManager.createUser(user);* */ dentro de *src/main/java/br/com/engrenantorres/questionmanager/config/WebSecurityConfig.java*

- Pronto! Agora você já pode se logar como administrador com o nome = *admin* e password = *123456* . 

- Aproveite o projeto. =)

- Se aparecer, na segunda vez em que você rodar o código aparecer o erro *Duplicate entry 'admin' for key 'users.PRIMARY'*:
  basta comentar o código *jdbcUserDetailsManager.createUser(user);* dentro de *src/main/java/br/com/engrenantorres/questionmanager/config/WebSecurityConfig.java*

## Tecnologias utilizadas

1- Java

2- Spring MVC

3- Jpa

4- Hibernate

5- MySQL or H2

6- Thymeleaf

7- Spring Security

8 - JDBC

9 - Bootstrap

![image](https://user-images.githubusercontent.com/85042807/199363469-9c23b828-364e-4f53-b58b-924fcf0550cf.png)


