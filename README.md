Steps to setup the project.

1. Make sure you have installed Maven.

2. Make sure there exist a database with name 'avance_pay_test_db' in MySQL server.

3. Adjust the properties regarding the username and pasword of database in application.properties file located here: src/main/resources

4. Open cmd, navigate to root folder(where pom.xml is located) and type 'mvn clean install'

5. Type 'java -jar target\avance-pay-test-0.0.1-SNAPSHOT.jar' and the application is deployed.
