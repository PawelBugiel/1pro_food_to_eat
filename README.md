# Food to Eat

Projekt treningowy.

---

## Spis treści
- [O projekcie](#o-projekcie)
- [Funkcjonalności](#funkcjonalności)
- [Wymagania wstępne](#wymagania-wstępne)
- [Instrukcja instalacji i uruchomienia](#instrukcja-instalacji-i-uruchomienia)
- [Sposób użycia](#sposób-użycia)
- [Użyte technologie](#użyte-technologie)
- [Status projektu](#status-projektu)
- [Licencja](#licencja)
- [Zrzuty ekranu lub demo](#zrzuty-ekranu-lub-demo)
- [Lista zadań](#lista-zadań)
- [Kontakt lub współpraca](#kontakt-lub-współpraca)
- [Dane logowania aplikacji (dla środowiska deweloperskiego)](#dane-logowania-aplikacji-dla-środowiska-deweloperskiego)

---

## O projekcie
**Food to Eat** to aplikacja webowa typu CRUD (Create, Read, Update, Delete) służąca do zarządzania produktami spożywczymi pod kątem ich daty przydatności do spożycia. Głównym celem biznesowym projektu jest pomoc użytkownikom w monitorowaniu świeżości żywności, minimalizując marnotrawstwo.

Projekt ten pełni rolę mojej **piaskownicy deweloperskiej**, umożliwiając mi praktyczną naukę i eksperymentowanie z nowoczesnymi technologiami w obszarze backendu, frontendu i developmentu. Jego kluczowe cele to:
* **Integracja poznanych technologii**: Łączenie różnych aspektów programowania, które wcześniej były wykorzystywane w mniejszych, izolowanych aplikacjach treningowych.
* **Rozwiązywanie problemów**: Aktywne napotykanie i efektywne rozwiązywanie wyzwań programistycznych, co buduje doświadczenie w debugowaniu i optymalizacji.
* **Architektura warstwowa**: Budowa aplikacji z jasno 'zdefiniowanymi warstwami (prezentacji, logiki biznesowej, dostępu do danych) dla lepszej modułowości i łatwości utrzymania.
* **Praca z relacyjnymi bazami danych**: - H2, MySQL, lokalnie, chmurowo z użyciem AWS EBS  
* **Tworzenie REST API**: Projektowanie i implementacja elastycznego REST API, które będzie służyć jako punkt komunikacji dla klienta frontendowego.
* **Testowanie**: Pokrywanie kodu testami jednostkowymi (JUnit)
* **Rozwój Frontendu**: Budowa interfejsu użytkownika przy użyciu frameworka **Vue.js**, co pozwala na pełne doświadczenie w tworzeniu aplikacji full-stack.

---

## Funkcjonalności
Obecnie aplikacja `Food to Eat` oferuje następujące kluczowe funkcjonalności:
* **Dodawanie produktów spożywczych**: Możliwość wprowadzania nowych produktów z ich nazwą, datą ważności i innymi istotnymi informacjami.
* **Wyświetlanie listy produktów**: Przeglądanie wszystkich dodanych produktów, prawdopodobnie z opcjami filtrowania i sortowania (np. według daty ważności).
* **Edycja informacji o produkcie**: Aktualizacja szczegółów istniejących produktów.
* **Usuwanie produktów**: Skuteczne usuwanie produktów z bazy danych.
* **Sortowanie listy**: Mozliwosc sortowania listy produktow według dowolnej kolumny
* **Wyszukiwanie dowolnego produktu po nazwie**: możliwość wpisywania dowolnego ciągu znaków
* **Paginacja**: Wyswietlenie listy produktow w paginacji

---

## Wymagania wstępne
Aby uruchomić projekt, będziesz potrzebować:
* **Docker**: Do łatwego uruchomienia aplikacji za pomocą obrazów kontenerów.

Jeśli wolisz uruchomić aplikację z kodu źródłowego (dla deweloperów), będziesz potrzebować również:
* **Java Development Kit (JDK)**: Wersja 8 lub nowsza.
* **Apache Maven**: Do zarządzania zależnościami i budowania projektu backendowego.
* **MySQL Database**: Lokalna instancja bazy danych MySQL do przechowywania danych.
* **Node.js**: Do uruchomienia i budowania aplikacji frontendowej Vue.js. Zalecana wersja 14 lub nowsza.
* **Git**: Do klonowania repozytorium.

---

## Instrukcja instalacji i uruchomienia

### Klonowanie repozytorium
Użyj komendy: `git clone https://github.com/TwojaNazwaUzytkownika/FoodToEat.git`
Następnie przejdź do katalogu projektu: `cd FoodToEat`

### Uruchomienie aplikacji za pomocą Docker Compose (zalecane)
To najprostszy sposób na uruchomienie całej aplikacji (backend + frontend + baza danych) za pomocą jednego polecenia.
1.  Upewnij się, że masz zainstalowany **Docker** i **Docker Compose**.
2.  Przejdź do głównego katalogu projektu: `cd FoodToEat`
3.  Uruchom aplikację: `docker-compose up -d`  
    *Aplikacja backendowa będzie dostępna pod adresem `http://localhost:8080`.*  
    *Aplikacja frontendowa będzie dostępna pod adresem `http://localhost:8081`.*


### Uruchomienie aplikacji z kodu źródłowego (dla deweloperów)

Jeśli chcesz uruchomić poszczególne komponenty z kodu źródłowego, postępuj zgodnie z poniższymi instrukcjami:

#### Konfiguracja bazy danych (MySQL)
1.  Utwórz nową bazę danych MySQL (np. `food_to_eat_db`).
2.  Zaktualizuj plik `src/main/resources/application.properties` (lub `application.yml`) w projekcie Spring Boot, podając dane dostępowe do swojej bazy danych:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/food_to_eat_db?createDatabaseIfNotExist=true
    spring.datasource.username=twoja_nazwa_uzytkownika_mysql
    spring.datasource.password=twoje_haslo_mysql
    spring.jpa.hibernate.ddl-auto=update # lub create, jeśli chcesz automatycznie tworzyć tabele
    ```

#### Uruchomienie Backendu (Spring Boot)
1.  Przejdź do katalogu głównego projektu backendowego (`/FoodToEat/backend`).
2.  Zbuduj projekt za pomocą Mavena, używając komendy: `mvn clean install`
3.  Uruchom aplikację Spring Boot komendą: `mvn spring-boot:run`  
    Aplikacja backendowa będzie dostępna domyślnie pod adresem `http://localhost:8080`.

#### Uruchomienie Frontendu (Vue.js)
1.  Przejdź do katalogu głównego projektu frontendowego (`/FoodToEat/frontend`).
2.  Zainstaluj zależności Node.js, używając komendy: `npm install`
3.  Uruchom aplikację Vue.js w trybie deweloperskim komendą: `npm run serve`  
    Aplikacja frontendowa będzie dostępna domyślnie pod adresem `http://localhost:8081` (lub innym wskazanym w konsoli).

---

## Sposób użycia
Po uruchomieniu aplikacji (za pomocą Docker Compose lub z kodu źródłowego):
1.  Otwórz przeglądarkę i przejdź do adresu aplikacji frontendowej (np. `http://localhost:8081`).
2.  Użyj danych logowania dla użytkownika lub administratora (szczegóły poniżej w sekcji "Dane logowania aplikacji").
3.  Interfejs użytkownika pozwoli Ci na dodawanie, przeglądanie, edytowanie i usuwanie produktów spożywczych.
4.  Dostęp do dokumentacji API (Swagger UI) dla endpointów backendowych będzie możliwy pod adresem: `http://localhost:8080/swagger-ui.html`.

---

## Użyte technologie
Projekt `Food to Eat` wykorzystuje następujące kluczowe technologie i narzędzia:

### Backend
* **Java 8+**: Podstawowy język programowania.
* **Spring Boot**: Framework do szybkiego tworzenia aplikacji opartych na Javie.
* **Apache Maven**: Narzędzie do zarządzania projektem i budowania.
* **Spring Data JPA / Hibernate**: Do obsługi bazy danych i mapowania obiektowo-relacyjnego.
* **Lombok**: Upraszcza generowanie kodu boilerplate (gettery, settery itp.).

### Baza Danych
* **MySQL**: Relacyjna baza danych.

### Testowanie
* **JUnit 5**: Framework do testów jednostkowych.
* **Mockito**: Biblioteka do tworzenia obiektów mockowych w testach.
* **JaCoCo**: Narzędzie do analizy pokrycia kodu testami.

### Narzędzia Deweloperskie
* **IntelliJ IDEA**: Zintegrowane środowisko programistyczne (IDE).
* **Git**: System kontroli wersji.
* **Git Bash**: Emulator powłoki Unix dla systemu Windows.
* **Postman**: Narzędzie do testowania i dokumentowania API.
* **OpenAPI (Swagger)**: Do generowania interaktywnej dokumentacji REST API.

### Frontend
* **Vue.js 3**: Progresywny framework JavaScript do budowy interfejsów użytkownika.
* **Node.js**: Środowisko uruchomieniowe JavaScript.
* **npm / Yarn**: Menedżery pakietów dla Node.js.

### DevOps / Deployment
* **Docker**: Technologia konteneryzacji.
* **Docker Compose**: Do orkiestracji wielu kontenerów.
* **GitHub Actions**: Do automatyzacji procesów CI/CD (planowane).

---

## Status projektu
Projekt `Food to Eat` jest w **aktywnej fazie rozwoju (W toku)**. Jest to projekt treningowy, co oznacza, że jego głównym celem jest nauka i eksperymentowanie. Nowe funkcjonalności są sukcesywnie dodawane, a istniejące ulepszane.

---

## Licencja
Projekt jest prywatny i nie jest przeznaczony do publicznego użytku ani modyfikacji bez zgody autora.

---

## Zrzuty ekranu lub demo
*............*

---

## Lista zadań
Poniżej znajduje się lista planowanych i zrealizowanych zadań dla projektu:

* **Backend**
    * [x] Konfiguracja bazy danych (MySQL).
    * [x] Implementacja podstawowego REST API dla operacji CRUD.
    * [x] Implementacja autentykacji i autoryzacji (JWT/Spring Security).
    * [ ] Dodanie logowania (np. Logback/SLF4J).
    * [ ] Pokrycie kodu backendu testami jednostkowymi i integracyjnymi.
    * [ ] Integracja z zewnętrznym API (np. Open Food Facts API dla danych produktów).
* **Frontend**
    * [ ] Utworzenie podstawowego interfejsu użytkownika w Vue.js.
    * [ ] Rozwój interfejsu użytkownika w Vue.js.
    * [ ] Implementacja formularzy do dodawania/edycji produktów.
    * [ ] Widok listy produktów z opcjami sortowania/filtrowania.
    * [ ] Obsługa błędów i walidacja danych na poziomie UI.
* **Deployment & DevOps**
    * [x] Przygotowanie konfiguracji Docker Compose dla łatwego uruchamiania.
    * [ ] Konfiguracja Continuous Integration/Continuous Deployment (CI/CD) (np. GitHub Actions).
    * [ ] Wdrożenie aplikacji na platformie chmurowej (np. AWS, Oracle Cloud).
* **Dokumentacja**
    * [ ] Rozszerzenie dokumentacji API (Swagger).
    * [ ] Finalizacja dokumentacji README.md.

---

## Kontakt lub współpraca
Jeśli masz pytania, sugestie lub chciałbyś nawiązać współpracę, skontaktuj się ze mną:

**Paweł Bugiel**
* LinkedIn: [https://www.linkedin.com/in/twoje-imie-nazwisko](https://www.linkedin.com/in/twoje-imie-nazwisko) *(Opcjonalnie: Zastąp swoim linkiem do profilu LinkedIn)*
* Email: pawel.bugiel@outlook.com

---

## Dane logowania aplikacji (dla środowiska deweloperskiego)
Te dane są przeznaczone do szybkiego testowania aplikacji w środowisku deweloperskim.
* **Administrator**:
    * Login: `admin@admin`
    * Hasło: `Admin22*`
* **Użytkownik**:
    * Login: `user@user`
    * Hasło: `User123*`

---

<p align="right"><a href="#top-of-this-page">^ Wróć na górę strony ^</a></p>