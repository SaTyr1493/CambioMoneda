FROM openjdk:8
ADD target/CambioMoneda-1.0.0.jar CambioMoneda-1.0.0.jar
EXPOSE 8085
ENTRYPOINT ["java","-jar","CambioMoneda-1.0.0.jar"]