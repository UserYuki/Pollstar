FROM node:14.18.0 as front-end
WORKDIR /front-end
COPY front-end .
RUN npm ci
RUN npm run-script build
FROM gradle as pollstar
WORKDIR /pollstar
COPY pollstar .
RUN mkdir -p src/main/resources/static
COPY --from=front-end /front-end/build src/main/resources/static
RUN gradle build -x test
FROM openjdk:11
COPY --from=pollstar /pollstar/build/libs/pollstar-0.0.1-SNAPSHOT.jar ./app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
EXPOSE 8080
RUN adduser user
USER user
CMD [ "sh", "-c", "java -Dserver.port=$PORT -Djava.security.e gd=file:/dev/./urandom -jar app.jar" ]