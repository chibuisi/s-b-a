FROM openjdk
ADD target/s-b-a.jar s-b-a.jar
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/s-b-a.jar"]