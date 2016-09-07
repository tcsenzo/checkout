# Set the base image to java8
FROM vixns/java8

MAINTAINER Leonardo Wolter <leocwolter@gmail.com>

# Define environment variables
ENV CHECKOUT_HOME=/opt/checkout

# Add artifacts
ADD build/checkout.jar $CHECKOUT_HOME/checkout.jar
ADD build/application.properties $CHECKOUT_HOME/application.properties
ADD run.sh $CHECKOUT_HOME/run.sh

# Main command
ENTRYPOINT $CHECKOUT_HOME/run.sh