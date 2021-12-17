FROM doptopenjdk/openjdk11:latest
VOLUME /tmp
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
