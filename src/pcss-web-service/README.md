# PCSS WEB SERVICE

A Collection of SOAP endpoints for PCSS

## Configuration

| Environment Variable  | Description   | Notes   |
| --- | --- | --- |
| ORDS_PCSS_BASEPATH | ORDS PCSS SERVICE URL |   |
| ORDS_PCSS_USERNAME | ORDS PCSS SERVICE USERNAME |   |
| ORDS_PCSS_PASSWORD | ORDS PCSS SERVICE PASSWORD |   |

# Currently Configured to build a WAR file for running on JBoss-EAP-7.3
- Find and download a copy of jboss-eap-7.3.0.zip to the pcss-web-service folder
- mvn clean install
- docker image build . -t jboss-eap-7.3-pcss
- docker run --rm --detach --publish 8080:8080 --name pcss-web-service jboss-eap-7.3-pcss
- service should be up and running at http://localhost:8080/pcss-web-service/ws/
