FROM maven:3.8.6-openjdk-18 as build

WORKDIR /code
COPY . /code/
RUN mvn --batch-mode clean package verify

FROM openliberty/open-liberty:full-java17-openj9-ubi

ARG VERSION=1.0.0

LABEL \
  env="-PROD" 

COPY --chown=1001:0 --from=build\
    /code/src/main/liberty/config \
    /config/

COPY --chown=1001:0 --from=build\
    /code/target/sample-app.war \
    /config/apps

COPY --chown=1001:0 --from=build\
    /code/log4j2.xml \
    /config/

RUN configure.sh