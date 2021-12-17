FROM openjdk:11-jdk-alpine
VOLUME /tmp
COPY pollstar/build/libs/*.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]