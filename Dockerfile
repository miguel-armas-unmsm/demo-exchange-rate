FROM openjdk:11
COPY "./target/exchange-rate-api-1.0.0.jar" "exchange-rate-api.jar"
EXPOSE 8090
ENTRYPOINT ["java","-jar","exchange-rate-api.jar"]