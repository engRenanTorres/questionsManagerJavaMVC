FROM openjdk

WORKDIR /APP

COPY target/questionmanager-0.0.1-SNAPSHOT.jar /app/questionmanager.jar

ENTRYPOINT ["java", "-jar", "questionmanager.jar"]