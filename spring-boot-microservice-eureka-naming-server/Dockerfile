FROM java:8
#HEALTHCHECK --interval=5s --start-period=60s --timeout=5s \
#  CMD curl http://127.0.0.1:8761/actuator/health | grep 'UP' || exit 1
LABEL maintainer="mgs2bio@gmail.com"
EXPOSE 8761
ADD target/spring-boot-microservice-eureka-naming-server-0.0.1-SNAPSHOT.jar //
ENTRYPOINT ["java", "-jar", "/spring-boot-microservice-eureka-naming-server-0.0.1-SNAPSHOT.jar"]
