FROM maven:3.5-jdk-8-alpine
COPY . .
RUN mvn install

#FROM openjdk:8-jdk-alpine 
#VOLUME /tmp
#COPY /target/springboot-batch-1.0-SNAPSHOT.jar app.jar 
EXPOSE 8080  
RUN sh -c "cp /target/springboot-batch-1.0-SNAPSHOT.jar app.jar"
ADD test_hello.sh .
RUN chmod +x test_hello.sh
CMD sh test_hello.sh