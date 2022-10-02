FROM adoptopenjdk/openjdk14:alpine-jre
ADD build/libs/*.jar orders-consumer.jar
ENV JAVA_OPTS=""
EXPOSE 7002
ENTRYPOINT ["java", "-jar", "orders-consumer.jar"]