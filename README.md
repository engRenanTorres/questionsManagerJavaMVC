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
- Pronto! Agora você já pode se logar como administrador com o nome = *admin* e password = *123456* .
  Este usuário tem autoridade de ADM. E pode adicionar ou remover questões.

- Os demais usuários podem ser criados normalmente pelo link "cadastre-se" na tela inicial.

- Aproveite o projeto. =)

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


