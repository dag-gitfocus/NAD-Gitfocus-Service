GitFocus-Service Application
============================

Technologies Used:
------------------
Spring Boot, PostgreSQL, JPA, Hibernate, Angular8 and Log4j2

Steps to Setup
==============
1. Clone the repository
------------------------

==> https://github.com/dag-gitfocus/GitFocus_Service.git

2. Configure PostgreSQL
-----------------------

==> Create a database named gitFocus and schema postgres 

==> Then, open src/main/resources/application.properties file and change the spring datasource username and password as per your PostgreSQL installation.

3. Log4j2 
---------

==> Create folder in C:// and name like 'GitFocusLogs' to track log messages and events

4. Run the app
---------------
Type the following command from the root directory of the project to run it -

1. Run As -> Spring Boot App

2. mvn spring-boot:run

Alternatively, you can package the application in the form of a JAR file and then run it like so -

mvn clean package

java -jar target/GitFocus-Service-0.0.1-SNAPSHOT.jar


****  For Team Members, Must follow indentation with four space and coding standards ****