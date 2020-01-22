# currencyconverter-spring-boot-rest-api
Simple Currency Converter using REST API with Spring Boot platform. This API enable to provide us historical data of the currency country available from fixer.io country list.

This project divided into two parts, currency converter via web browser and also via JSON response



-- basic url to get exchange Rate from base Currency to intended convert Currency --

http://localhost:9000/exchangeRate?date=2020-01-18&baseCurrency=MYR&convertCurrency=USD&amount=1

--- GUI entrypoints ---
http://localhost:9000/index


-- Running to get the jar file ---
mvn clean install

--- Running the jar ---
java -jar currencyconverter-0.0.1-SNAPSHOT.jar

--- Rest documentation using Swagger---
http://localhost:9000/swagger-ui.html#/

--- Running Jacoco Reports for checking test coverage ---
mvn test jacoco:report

--- API key can get from fixer.io ---

Thank you





