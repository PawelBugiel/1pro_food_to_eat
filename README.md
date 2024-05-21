### Food_to_eat. A CRUD training project.

1. [About the application](#About-the-application) - Short description of the project
<br><br>
2. [Used technologies, tools](#Used-technologies,-tools) - as it says
<br><br>
3. [What I experienced](#What-I-experienced) - what are my experiences that I took out of it.
<br><br>
4. [To do list](#4.-To-do-list) - What is done and what still to do
<br><br>
5. [Appendix](#Appendix) - A bit more information about my approach to write this application. 

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

> #### About the application

A CRUD application whose main goals are:
- combine different learned aspects, previously used in separate tiny training applications
- encounter problems and solving them
- build a layered application
- create rest api
- cover the code with tests
- get experience

> #### Used technologies, tools

* Java 8 - mainly
* Spring Boot 
* Hibernate
* Database:
  * at the very beginning MySQL, 
  * then AWS RDS
  * finally an in-memory H2
* Lombok
* JUnit 
* Mocikto
* JaCoCo
* OpenAPI Swagger
* Postman
* IntelliJ
* Maven
* GitBash

> #### What I experienced
#### Encountered problems or what was new for me : 

- configuring the spring boot logging levels in different ways 
- using UUID 
- deploying the application to AWS RDS
- knowledge about the n+1 problem
- handling exceptions in a different way - AOP
- mapping objects 
- debugging
- using the Builder pattern
- work with LocalDate.class as an entity property
- ... and couple others

> To do list
- [ ] rest API
- [ ] Tests
- [x] Database
- [ ] Integration with Open Food Facts API
- [ ] Refactor

> #### Appendix
Do tego projektu podchodziłem kilka razy. Mam kilka jego wersji. Wersja przedstawiona tutaj jest:
* najbardziej zaawansowana, jeśli chodzi o kompletność jej kodu
* najprostsza, jeśli chodzi o złożoność biznesową, funkcjonalności
  <br>

Powyższe wyniknęło z "pomysłowości" nowicjusza. Mianowicie.
Na samym początku myślałem, że napiszę aplikację do zarządzania zasobami produktów spożywczych w domu, z takimi "bajerami" jak :
<br>
Encje: Product, Location,
<br>
Bazową funkcjonalnością miało być rejestrowanie produktów spożywczych, z datą ich przydatności do użycia oraz możliwością wyszukiwania produktów, których data przydatności jest bliska upłynięcia.
<br>
<br>
Dodatkowo planowałem tworzenie fizycznych lokalizacji w domu, do których można było przypisywać produkty. Po to aby móc je w domu odnaleźć.
<br>
Baza danych miała być opart na kilku tabelach, z relacjami między tabelami: 1:1 , N :1, N:M

n+1  problem
<br> <br>
LocalDate.class serialization
<br> <br>
Problem with UUID
~~~
"...ERROR o.h.e.jdbc.spi.SqlExceptionHelper- Incorrect string value: '\xE5ENi\x81L...' for column 'id' at row 1..."
~~~
Stackoverflow - w pierwotnej wersji aplikacji, na bazie danych z bi-relacyjnie powiązanymi tabelami. Zapętlało pobieranie danych.