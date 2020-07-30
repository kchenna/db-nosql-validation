#!/bin/bash
#echo $SLEEP_TIME
echo “Starting script ………..”
echo “Put your waiting code here, I will wait for 1 min”
sleep 30
echo “slept well for a minute”
java -Djava.security.egd=file:/dev/./urandom -jar /app.jar