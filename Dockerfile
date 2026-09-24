#############################################################################################
###              Stage where Docker is building spring boot app using maven               ###
#############################################################################################
FROM maven:3.9.9-eclipse-temurin-17 as build

WORKDIR /

COPY . .

RUN mvn clean package -Dmaven.test.skip=true

#############################################################################################

#############################################################################################
### Stage where Docker is running a java process to run a service built in previous stage ###
#############################################################################################
FROM eclipse-temurin:17-jre-alpine

RUN apk update \
    && apk add --upgrade --no-cache libexpat \
    && apk add --upgrade --no-cache libpng \
    && apk add --upgrade --no-cache openssl \
    && apk add --upgrade --no-cache gnutls

ARG SERVICE_NAME=pcss-civil-application

COPY --from=build ./${SERVICE_NAME}/target/${SERVICE_NAME}.jar /app/service.jar

CMD ["java", "-Xmx1g", "-jar", "/app/service.jar"]
#############################################################################################
