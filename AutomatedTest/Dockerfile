FROM openjdk:11-jre-slim

WORKDIR /app
RUN chgrp -R 0 /app && \
    chmod -R g=u /app

COPY ./target/scss-test-runner.jar .

ENTRYPOINT ["java","-Xss2048k","-jar","scss-test-runner.jar"]
