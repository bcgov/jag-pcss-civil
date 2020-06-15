
### Set the base image  
FROM jboss/base-jdk:11 

# file author / maintainer
MAINTAINER "Shreyas Devalapurkar" "shreyas.devalapurkar@gov.bc.ca"

# Set the JBOSS_VERSION env variable
ENV JBOSS_VERSION 7.3.0
ENV JBOSS_HOME /opt/jboss/jboss-eap-7.3
ENV JBOSS_MODULES $JBOSS_HOME/modules

# debugging HOME
RUN echo "HOME: $HOME"

# debugging JBOSS_HOME
RUN echo "JBOSS_HOME: $JBOSS_HOME"

# debugging JBOSS_MODULES
RUN echo "JBOSS_MODULES: $JBOSS_MODULES"

COPY jboss-eap-$JBOSS_VERSION.zip $HOME

# Add the JBoss distribution to /opt, and make jboss the owner of the extracted zip content
# Make sure the distribution is available from a well-known place
RUN cd $HOME \
    && unzip jboss-eap-$JBOSS_VERSION.zip \
    && rm jboss-eap-$JBOSS_VERSION.zip

# Ensure signals are forwarded to the JVM process correctly for graceful shutdown
ENV LAUNCH_JBOSS_IN_BACKGROUND true

# Copy war to deployments folder
# ADD ./pcss-web-service.war $JBOSS_HOME/standalone/deployments/pcss-web-service.war
ADD ./target/pcss-web-service-0.0.1-SNAPSHOT.war $JBOSS_HOME/standalone/deployments/pcss-web-service.war

# User root to modify war owners
USER root

# Modify owners war
RUN chown jboss:jboss $JBOSS_HOME/standalone/deployments/pcss-web-service.war

# Important, use jboss user to run image
USER jboss

# Add a user in administration realm
RUN $JBOSS_HOME/bin/add-user.sh admin foobar@123 --silent

# Expose the ports we're interested in
EXPOSE 8080 9990

# Set the default command to run on boot
# This will boot JBoss EAP in the standalone mode and bind to all interface
CMD ["/opt/jboss/jboss-eap-7.3/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]


# Docker command examples:
# docker image build . -t jboss-eap-7.3-pcss
# docker run --rm --detach --publish 8080:8080 --name pcss-web-service jboss-eap-7.3-pcss


