FROM openjdk:11
VOLUME /tmp
COPY ../pollstar/build/libs/*.jar app.jar
RUN echo ls
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
