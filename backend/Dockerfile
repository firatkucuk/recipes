FROM openjdk:11-jre-slim

ADD target/recipes.jar app.jar

EXPOSE 9090

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar", "1>/dev/stdout", "2>/dev/stderr"]
