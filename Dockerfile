FROM openjdk:11
EXPOSE 8080
ADD target/restapi.jar restapi.jar
ENTRYPOINT ["java","-jar","/restapi.jar"]