FROM openjdk:11-jre-slim

COPY ./pcss-civil-application/target/pcss-civil-application.jar pcss-civil-application.jar

ENTRYPOINT ["java","-jar","/pcss-civil-application.jar"]