FROM eclipse-temurin:17-jre-alpine

#CVE Fixes
RUN apk -U upgrade
RUN apk update && apk add --upgrade --no-cache libexpat libpng gnupg # CVE fixes
RUN apk del --force libpng ttf-dejavu
RUN rm -rf /lib/apk/db/installed/libpng

COPY ./pcss-civil-application/target/pcss-civil-application.jar pcss-civil-application.jar

ENTRYPOINT ["java", "-Xmx1g", "-jar", "/pcss-civil-application.jar"]

