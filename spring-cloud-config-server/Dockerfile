FROM java:8
#HEALTHCHECK --interval=5s --start-period=80s --timeout=5s \
#  CMD curl http://127.0.0.1:8222/actuator/health | grep 'UP' || exit 1
LABEL maintainer="mgs2bio@gmail.com"
ADD target/spring-cloud-config-server-1.0.jar //
ENTRYPOINT ["java", "-jar", "/spring-cloud-config-server-1.0.jar"]
