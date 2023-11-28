FROM eclipse-temurin:17-jre-alpine

RUN apk upgrade expat  # Fix for CVE-2022-43680

WORKDIR /app
RUN chgrp -R 0 /app && \
    chmod -R g=u /app

COPY ./target/pcss-test-runner.jar .

ENTRYPOINT ["java","-Xss2048k","-jar","pcss-test-runner.jar"]
