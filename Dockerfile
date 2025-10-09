FROM eclipse-temurin:17-jre-alpine

RUN apk update && apk add --no-cache libexpat=2.7.3-r0

COPY ./pcss-civil-application/target/pcss-civil-application.jar pcss-civil-application.jar

ENTRYPOINT ["java", "-Xmx1g", "-jar", "/pcss-civil-application.jar"]

