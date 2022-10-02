FROM adoptopenjdk/openjdk14:alpine-jre
ADD build/libs/*.jar order-service.jar
ENV JAVA_OPTS=""
EXPOSE 7001
ENTRYPOINT ["java", "-jar", "order-service.jar"]