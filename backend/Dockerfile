FROM maven:3.8.4-eclipse-temurin-17 as builder
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk-alpine
COPY --from=builder /target/syncmeet-0.0.1-SNAPSHOT.jar syncmeet-0.0.1-SNAPSHOT.jar
EXPOSE 8080
CMD ["java","-jar","syncmeet-0.0.1-SNAPSHOT.jar"]