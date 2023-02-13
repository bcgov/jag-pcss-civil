FROM eclipse-temurin:11-jre-alpine

RUN apk upgrade expat  # Fix for CVE-2022-43680

COPY ./pcss-civil-application/target/pcss-civil-application.jar pcss-civil-application.jar

ENTRYPOINT ["java", "-Xmx1g", "-jar", "/pcss-civil-application.jar"]
