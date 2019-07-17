FROM java:8
HEALTHCHECK --interval=5s --timeout=3s \
  CMD curl http://192.168.99.100:8111/actuator/health | grep 'UP' || exit 1
LABEL maintainer="mgs2bio@gmail.com"
ADD target/ZuulProxyExample-0.0.1-SNAPSHOT.jar //
ENTRYPOINT ["java", "-jar", "/ZuulProxyExample-0.0.1-SNAPSHOT.jar"]
