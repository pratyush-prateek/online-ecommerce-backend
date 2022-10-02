FROM adoptopenjdk/openjdk14:alpine-jre
ADD build/libs/*.jar product-service.jar
ENV JAVA_OPTS="-Djdk.tls.client.protocols=TLSv1.2"
EXPOSE 7003
ENTRYPOINT ["java", "-jar", "product-service.jar"]