FROM openjdk:11
Expose 8080
ADD ../target/russian-api.jar russian-api.jar
ENTRYPOINT ["java", "-jar", "/russian-api.jar"]