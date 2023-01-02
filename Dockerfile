FROM openjdk:17-alpine

#Cria usuario para nao rodar no root
RUN addgroup -S spring && adduser -S spring -G spring
#Troca para o usuairo com nome spring
USER spring:spring

#WORKDIR /target
#Coloca o arquivo executavel na variavel jar
ARG JAR_FILE=/target/*.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]