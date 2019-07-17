FROM java:8
#HEALTHCHECK --interval=5s --start-period=80s --timeout=5s \
#  CMD curl http://127.0.0.1:8000/actuator/health | grep 'UP' || exit 1
LABEL maintainer="mgs2bio@gmail.com"
ADD target/spring-boot-microservice-forex-service-0.0.1-SNAPSHOT.jar //
ENTRYPOINT ["java", "-jar", "/spring-boot-microservice-forex-service-0.0.1-SNAPSHOT.jar"]
