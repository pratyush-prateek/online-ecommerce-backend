FROM adoptopenjdk/openjdk14:alpine-jre
ADD build/libs/*.jar service-discovery.jar
ENV JAVA_OPTS=""
EXPOSE 7004
ENTRYPOINT ["java", "-jar", "service-discovery.jar"]