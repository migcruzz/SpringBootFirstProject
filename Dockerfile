
FROM openjdk:11-jre-slim


VOLUME /tmp


COPY target/demo-0.0.1-SNAPSHOT.jar app.jar

# Defina o ponto de entrada para a aplicação
ENTRYPOINT ["java", "-jar", "/app.jar"]
