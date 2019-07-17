FROM java:8
#HEALTHCHECK --interval=5s --start-period=80s --timeout=5s \
# CMD curl http://127.0.0.1:8100/actuator/health | grep 'UP' || exit 1
LABEL maintainer="mgs2bio@gmail.com"
ADD target/spring-boot-microservice-currency-conversion-1.0.jar //
ENTRYPOINT ["java", "-jar", "/spring-boot-microservice-currency-conversion-1.0.jar"]
