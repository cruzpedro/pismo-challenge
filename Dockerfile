FROM openjdk:17-alpine as up-stage

# The application's jar file
ARG JAR_FILE=pismo-challenge/target/*.jar

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Add the application's jar to the container
ADD ${JAR_FILE} app.jar

# Run the jar file
ENTRYPOINT ["java", "-agentlib:jdwp=transport=dt_socket,address=8787,server=y,suspend=n", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]
