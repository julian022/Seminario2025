FROM amazoncorretto:17

ARG JAR_FILE=build/libs/demo-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} demo.jar

EXPOSE 8085

ENTRYPOINT ["java", "-jar", "/demo.jar", "--server.address=0.0.0.0"]