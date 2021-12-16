FROM gradle:7.2.0-jdk11

# application placed into /opt/app
RUN mkdir -p /opt/vendor
WORKDIR /opt/vendor

# selectively add the POM file and
# install dependencies
COPY build.gradle /opt/vendor
RUN gradle init

# rest of the project
COPY src /opt/vendor/src

# local application port
EXPOSE 8081

# execute it
CMD ["gradle", "Pollstar.java"]