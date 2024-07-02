### Food_to_eat. The very first CRUD training project.
Contents:
1. [About the application](#About-the-application) - Short description of the project
   <br><br>
2. [Used technologies and tools](#Used-technologies-and-tools)
<br><br>
3. [New experiences during coding](#New-experiences-during-coding) - what are my experiences that I took out of it.
<br><br>
4. [To do list](#To-do-list) - what is done and what still to do
<br><br>
5. [How to run and test the application](#How-to-run-and-test-the-application)
   <br><br>
6. [Appendix](#Appendix) - a bit more information about my approach to write this application.

### About the application

A CRUD application which main goals are:
- combine different learned aspects, previously used in separate tiny training applications
- encounter problems and solving them
- build a layered application
- create rest api
- cover the code with tests
- get experience

### Used technologies and tools

* Java 8 - mainly
* Spring Boot 
* Hibernate
* Database:
  * at the very beginning MySQL, 
  * then AWS RDS
  * finally in-memory H2
* Lombok
* JUnit 
* Mocikto
* JaCoCo
* OpenAPI Swagger
* Postman
* IntelliJ
* Maven
* GitBash

### New experiences during coding

- using UUID
- knowledge about the n+1 problem
- work with LocalDate.class as an entity property
- configuring the spring boot logging levels in different ways 
- mapping objects
- deploying the database to AWS RDS
- handling exceptions in a different way - AOP
- using the Java Code Coverage tool
- debugging
- using the Builder pattern
- ... and couple others

> ### To do list
- [x] Database
- [x] Rest API
- [ ] Tests
- [ ] Integration with Open Food Facts API
- [ ] Probably create a frontend

### How to run and test the application

Works for Windows, Linux, macOS.
1. Download and install the latest [Java Runtime Enviroment (JRE)](https://www.example.com "Java Runtime Environment")
2. Using a chosen CLI, check if installation passed successfully, by typing
   ~~~
    java -version
   ~~~
   As a result you should see a similar response
   ~~~
    java version "21.0.3" 2024-04-16 LTS
    Java(TM) SE Runtime Environment (build 21.0.3+7-LTS-152)
    Java HotSpot(TM) 64-Bit Server VM (build 21.0.3+7-LTS-152, mixed mode, sharing)
   ~~~
3. Download foodtoeat.jar
4. Again using the CLI, go to a folder that contains the downloaded file and run it with
   ~~~ 
    java -jar foodtoeat.jar 
   ~~~
5. Use the following URL with a web browser http://localhost:8081/swagger-ui/index.html#/
6. You should see a similar page 
7. To stop the application press CTRL + C in the CLI. 
 
### Appendix

I approached this project several times. The version presented here is:
* the most advanced in terms of code completeness
* the simplest in terms of business complexity
  <br>

At the very beginning, I thought that I would immediately write an application that goes beyond a typical crud functionality with a database based on several tables related by 1:1, N:1, N:M relationships.
The first project, novice-level experience, too much complexity for the first application and limited time forced me to simplify it to its current form.

First step is done. Now I feel much comfortable to approach a new personal project that I have in my mind :). 

