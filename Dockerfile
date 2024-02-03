FROM maven:3.8.4-openjdk-17 as build
WORKDIR /app
COPY pom.xml ./

COPY src src
RUN mvn package -DskipTests

FROM azul/zulu-openjdk-alpine:15
WORKDIR /app
COPY --from=build /app/target/AuthenticationServer-0.0.1-SNAPSHOT.jar /app/authentication.jar
EXPOSE 8080
CMD ["java", "-jar", "authentication.jar"]
