FROM openjdk:11
VOLUME /tmp
RUN ls /tmp
RUN tree
COPY pollstar/build/libs/*.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
