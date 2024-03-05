FROM gradle:7-jdk17 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle buildFatJar --no-daemon

FROM openjdk:17
EXPOSE 8080
RUN mkdir /app
WORKDIR /app
COPY build/libs/*.jar /app.jar
COPY .env .
ENTRYPOINT ["java","-jar","/app.jar"]