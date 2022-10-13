FROM adoptopenjdk:16.0.1_9-jdk-hotspot-focal
EXPOSE 8080
ADD target/elasticsearch-rest.jar elasticsearch-rest.jar
ENTRYPOINT ["java","-jar","/elasticsearch-rest.jar"]