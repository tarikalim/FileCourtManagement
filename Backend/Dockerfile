FROM openjdk:17 AS build
COPY pom.xml mvnw ./
COPY .mvn .mvn
RUN ./mvnw dependency:resolve
COPY src src
RUN ./mvnw package
FROM openjdk:17
WORKDIR /Backend
COPY --from=build target/*.jar Backend.jar
LABEL authors="Tarik"
ENTRYPOINT ["java", "-jar", "Backend.jar"]
