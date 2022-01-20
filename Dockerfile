FROM node as front-end
WORKDIR /front-end
COPY front-end .
RUN npm ci
RUN npm run-script build
FROM gradle as pollstar
WORKDIR /pollstar
COPY pollstar .
RUN mkdir -p src/main/resources/static
COPY --from=front-end /front-end/build src/main/resources/static
RUN gradle clean check
FROM openjdk:11
COPY --from=backend /pollstar/build/libs/pollstar-0.0.1-SNAPSHOT.jar ./app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
EEXPOSE 8080
RUN adduser -D user
USER user
CMD [ "sh", "-c", "java -Dserver.port=$PORT -Djava.security.egd=file:/dev/./urandom -jar app.jar" ]