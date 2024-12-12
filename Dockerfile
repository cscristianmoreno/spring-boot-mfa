FROM eclipse-temurin:21-jre

WORKDIR /app

ARG JAR_FILE=target/app.jar

COPY ${JAR_FILE} /app/

EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "app.jar" ]

