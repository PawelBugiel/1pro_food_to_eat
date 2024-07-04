### Food to eat
#### My very first training project.

<p id="top-of-this-page">List of contents:</p>

1. [About the project](#About-the-project)

2. [Used technologies and tools](#Used-technologies-and-tools)

3. [My new experiences during coding](#My-new-experiences-during-coding)

4. [To do list](#To-do-list)

5. [How to run the application](#How-to-run-the-application)

6. [Appendix](#Appendix) - a bit more information about my approach to write this application.

### About the project

It is a CRUD application that serves me as a development sandbox. Its main goals are:
- combining various aspects previously learned and used in separate tiny training applications
- encountering problems and solving them
- building a layered application
- creating a REST API
- covering the code with tests
- gaining experiences

Its business goal is to manage food products in terms of their suitability for consumption.   

### Used technologies and tools

* Database:
    * at the very beginning MySQL,
    * finally in-memory H2
* Java 8+
* Spring Boot 
* Hibernate
* Lombok
* JUnit
* Mockito
* Maven
* JaCoCo
* OpenAPI Swagger
* Postman
* IntelliJ
* Git
* GitBash
* GitHub

### My new experiences during coding

- UUID.class
- n+1 problem
- LocalDate.class as an entity property
- configuring of login levels
- stack overflow exception caused by bidirectional table relations
- DTOs 
- deploying MySQL database to Amazon Web Services - RDS
- handling exceptions with AOP approach
- Java Code Coverage tool
- Builder pattern
- usefulness of unit tests during refactoring
- ... and couple others

### To do list
- [x] Database
- [x] REST API
- [ ] Improve overall consistency
- [ ] Tests 
- [ ] Integration with Open Food Facts API
- [ ] Logging
- [ ] Any others that I don't know about yet ;)

### How to run the application

Windows, Linux, macOS, Android.  
*(In case of Android I recommend [Termux](https://play.google.com/store/apps/details?id=com.termux "Java Runtime Environment") as a  CLI.)* 


1. Download and install the latest [Java Runtime Enviroment (JRE)](https://www.example.com "Java Runtime Environment")

2. Use the chosen CLI to verify if the installation passed successfully, by typing
   ~~~
    java -version
   ~~~
   As a result you should see a similar response
   ~~~
    java version "21.0.3" 2024-04-16 LTS
    Java(TM) SE Runtime Environment (build 21.0.3+7-LTS-152)
    Java HotSpot(TM) 64-Bit Server VM (build 21.0.3+7-LTS-152, mixed mode, sharing)
   ~~~
3. Download a jar file from the GitHub - Releases section
4. Again use the CLI to go to a folder that contains the downloaded file and run it ( the jar file name may vary )
   ~~~ 
    java -jar foodToEat-0.0.1-SNAPSHOT.jar
   ~~~
5. Use the following URL with a web browser http://localhost:8081/swagger-ui/index.html#/
6. You should see a similar page. Use it to test the application.   
   <img class=border-image src="https://github.com/PawelBugiel/markdown-file-template/blob/main/.idea/resources/food_to_eat_openApi_UI.png?raw=true?raw=true" alt="Spring Boot" width="44%">
  
7. To stop the application press CTRL + C in the CLI.



To get a default Spring Boot - Open API documentation click the link just below the application title - (/v3/api-docs) 

### Appendix

I approached this project several times.
At the very beginning, I thought that I would immediately develop an application that goes beyond a typical CRUD functionality, with a database structured on several tables connected by 1:1, N:1, N:M relationships.
However, the first project, novice-level experience, too much complexity for the first application and limited time forced me to simplify it to its current form.

The version presented here is:
* the most advanced in terms of code completeness
* the simplest in terms of business complexity
  <br>

The first step is done. There are still some work to do.
And for the future, I already feel more comfortable approaching a new personal project that I have in mind :).

<a href="#top-of-this-page">^ Go to the top of the page ^</a>

