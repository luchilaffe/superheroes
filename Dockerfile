FROM openjdk:11

WORKDIR /app

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/superheroes-0.0.1-SNAPSHOT.jar"]