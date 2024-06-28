### Food_to_eat. The very first CRUD training project.

1. [About the application](#About-the-application) - Short description of the project
<br><br>
2. [Used technologies and tools](#Used-technologies-and-tools) - as it says
<br><br>
3. [New experiences during coding](#New-experiences-during-coding) - what are my experiences that I took out of it.
<br><br>
4. [To do list](#To-do-list) - what is done and what still to do
<br><br>
5. [Appendix](#Appendix) - a bit more information about my approach to write this application. 

<details>

<summary>Technologies, tools </summary>

<img src="https://github.com/PawelBugiel/md_test/blob/main/.idea/resources/java_6x3.png?raw=true" alt="Java" width="11%">
<img src="https://github.com/PawelBugiel/md_test/blob/main/.idea/resources/spring_6x3.png?raw=true" alt="Spring Boot" width="11%">
<img src="https://github.com/PawelBugiel/md_test/blob/main/.idea/resources/lombok_6x3.png?raw=true" alt="Lombok" width="11%">
<br>
<img src="https://github.com/PawelBugiel/md_test/blob/main/.idea/resources/hibernate_6x3.png?raw=true" alt="Hibernate" width="11%">
<img src="https://github.com/PawelBugiel/md_test/blob/main/.idea/resources/mysql_6x3.png?raw=true" alt="MySQL" width="11%">
<img src="https://github.com/PawelBugiel/md_test/blob/main/.idea/resources/h2_6x3.png?raw=true" alt="H2 in-memory database" width="8%">
<br>
<img src="https://github.com/PawelBugiel/md_test/blob/main/.idea/resources/junit_6x3.png?raw=true" alt="JUnit" width="9%">  
<img src="https://github.com/PawelBugiel/md_test/blob/main/.idea/resources/mockito_6x3.png?raw=true" alt="Mockito" width="11%"> 
<img src="https://github.com/PawelBugiel/md_test/blob/main/.idea/resources/jcoco_6x3.png?raw=true" alt="Jcoco" width="11%">
<br>
<img src="https://github.com/PawelBugiel/md_test/blob/main/.idea/resources/intellij_6x3.png?raw=true" alt="IntelliJ" width="11%">
<img src="https://github.com/PawelBugiel/md_test/blob/main/.idea/resources/maven_b_6x3.png?raw=true" alt="Maven" width="11%">
<img src="https://github.com/PawelBugiel/md_test/blob/main/.idea/resources/gitbash_6x3.png?raw=true" alt="GitBash" width="11%">
<br>
<img src="https://github.com/PawelBugiel/md_test/blob/main/.idea/resources/openapi_swagger_6x3.png?raw=true" alt="Swagger" width="11%">
<img src="https://github.com/PawelBugiel/md_test/blob/main/.idea/resources/postman_6x3.png?raw=true" alt="Postman" width="11%">
<br>

</details>

#### About the application

Download :
* the latest java SDK and install it
* download foodtoeat.jar 
* using a Windows or Linux CLI :
  * go to a folder that contains the downloaded file 
  * run it using  
  ~~~
  java foodtoeat.jar
  ~~~
* test the application using http://localhost:8081/swagger-ui/index.html#/ with a web browser


A CRUD application which main goals are:
- combine different learned aspects, previously used in separate tiny training applications
- encounter problems and solving them
- build a layered application
- create rest api
- cover the code with tests
- get experience

#### Used technologies and tools

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

#### New experiences during coding

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

> #### To do list
- [x] Database
- [x] Rest API
- [ ] Tests
- [ ] Integration with Open Food Facts API
- [ ] Create the Thymeleaf based frontend
 
> #### Appendix
Do tego projektu podchodziłem kilka razy. Mam kilka jego wersji. Wersja przedstawiona tutaj jest:
* najbardziej zaawansowana, jeśli chodzi o kompletność kodu
* najprostsza, jeśli chodzi o złożoność biznesową
  <br>

Kilkukrotne rozpoczynanie wyniknęło z "pomysłowości" nowicjusza. Mianowicie na początku sądziłem, że napiszę aplikację rozszerzoną o dodatkowe funkcjonalności, związane z magzynowaniem produktów, z bazą danych opartą na kilku tabelach powiązanych relacjami 1:1, N:1, N:M.
Pierwszy projekt, doświadczenie na poziomie nowicjusza, zbytnia złożoność oraz ograniczony czas zmusiły mnie do jej uproszczenia do obecnej postaci. 
Natomiast już mam pomysł na poboczny własny projekt, którego podejmę się w późniejszym czasie. 
