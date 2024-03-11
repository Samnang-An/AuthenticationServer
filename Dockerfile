FROM openjdk:17-jdk-alpine
EXPOSE 8080
ENV CONFIG_SERVER_URI=http://config-server:8888
RUN apk add --no-cache maven
WORKDIR /app
COPY ./ ./
RUN mvn package -DskipTests
CMD ["java", "-jar","/app/target/ConfigRemoteServer-0.0.1-SNAPSHOT.jar"]
