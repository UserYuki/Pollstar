FROM openjdk:11
VOLUME /tmp
COPY pollstar/build/libs/pollstar-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
