FROM maven:3.6.3-jdk-11 as builder
WORKDIR /backend
COPY . .
RUN mvn clean package

FROM eclipse-temurin:17-jdk-alpine
COPY --from=builder /backend/target/syncmeet-0.0.1-SNAPSHOT.jar syncmeet-0.0.1-SNAPSHOT.jar
EXPOSE 8080
CMD ["java","-jar","syncmeet-0.0.1-SNAPSHOT.jar"]