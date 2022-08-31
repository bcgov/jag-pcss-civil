FROM eclipse-temurin:11-jre-jammy

COPY ./pcss-civil-application/target/pcss-civil-application.jar pcss-civil-application.jar

ENTRYPOINT ["java","-jar","/pcss-civil-application.jar"]
