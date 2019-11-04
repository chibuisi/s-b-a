FROM openjdk
ADD target/chibuisi_s-b-a.jar chibuisi_s-b-a.jar
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/chibuisi_s-b-a.jar"]
