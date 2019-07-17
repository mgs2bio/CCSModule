FROM java:8
#HEALTHCHECK --interval=5s --start-period=60s --timeout=3s \
#  CMD curl http://127.0.0.1:9411/actuator/health | grep 'UP' || exit 1
LABEL maintainer="mgs2bio@gmail.com"
ADD target/zipkin-server-0.0.1-SNAPSHOT.jar //
ENTRYPOINT ["java", "-jar", "/zipkin-server-0.0.1-SNAPSHOT.jar"]
