FROM maven:3.8.8-eclipse-temurin-21 AS build
WORKDIR /app
COPY wait-for-it.sh /app/wait-for-it.sh
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["/wait-for-it.sh", "rabbitmq:5672", "--", "java", "-jar", "app.jar"]