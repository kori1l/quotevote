FROM maven:3.8.6 AS builder
WORKDIR /build
COPY . .
RUN mvn clean install -DskipTests

FROM openjdk:17
WORKDIR /opt/app
COPY --from=builder /build/target/quotevote.jar quotevote.jar
ENTRYPOINT ["java", "-jar", "quotevote.jar"]