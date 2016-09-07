#!/bin/bash

# Defaults
CHECKOUT_HEAP_OPTS=${CHECKOUT_HEAP_OPTS:--Xms512m -Xmx512m -XX:MaxMetaspaceSize=256m}

exec java -Dfile.encoding=UTF-8 $CHECKOUT_HEAP_OPTS -jar $CHECKOUT_HOME/checkout.jar --spring.config.location=file:///opt/checkout/application.properties