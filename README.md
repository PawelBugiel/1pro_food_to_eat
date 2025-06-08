# Waste no food

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
**Waste no food** to aplikacja webowa typu CRUD (Create, Read, Update, Delete) służąca do zarządzania produktami spożywczymi pod kątem ich **daty przydatności do spożycia**. Głównym celem biznesowym projektu jest **pomoc użytkownikom w monitorowaniu świeżości żywności**, minimalizując marnotrawstwo produktów spożywczych w gospodarstwach domowych.

Projekt ten pełni rolę mojej **piaskownicy deweloperskiej**, umożliwiając mi praktyczną naukę i eksperymentowanie z nowoczesnymi technologiami w obszarze backendu i frontendu. Jego kluczowe cele to:
* **Integracja poznanych technologii**: Łączenie różnych aspektów programowania, które wcześniej były wykorzystywane w mniejszych, izolowanych aplikacjach treningowych (np. Spring Boot, JPA, Vue.js).
* **Rozwiązywanie problemów**: Aktywne napotykanie i efektywne rozwiązywanie wyzwań programistycznych, co buduje doświadczenie w debugowaniu i optymalizacji kodu.
* **Architektura warstwowa**: Budowa aplikacji z jasno zdefiniowanymi warstwami (prezentacji, logiki biznesowej, dostępu do danych) dla lepszej modułowości, skalowalności i łatwości utrzymania.
* **Tworzenie REST API z bezpieczeństwem**: Projektowanie i implementacja elastycznego REST API zabezpieczonego mechanizmem **JWT (JSON Web Tokens)**, które będzie służyć jako punkt komunikacji dla klienta frontendowego.
* **Testowanie**: Pokrywanie kodu testami jednostkowymi i integracyjnymi (JUnit, Mockito), co zapewnia jakość i stabilność aplikacji oraz pozwala na weryfikację pokrycia kodu za pomocą JaCoCo.
* **Rozwój Frontendu**: Budowa intuicyjnego interfejsu użytkownika przy użyciu frameworka **Vue.js 3 z Pinia** (do zarządzania stanem) i **Vue Router** (do nawigacji), co pozwala na pełne doświadczenie w tworzeniu aplikacji full-stack.
* **Konteneryzacja**: Wykorzystanie Docker i Docker Compose do łatwego uruchamiania i zarządzania środowiskiem deweloperskim i produkcyjnym.
* **Integracja z zewnętrznym API**: Przygotowanie do integracji z zewnętrznymi źródłami danych, takimi jak **Open Food Facts API**, w celu wzbogacenia informacji o produktach.

<small>[Spis treści](#spis-treści)</small>    

---

## Funkcjonalności
Obecnie aplikacja `Waste no food` oferuje następujące kluczowe funkcjonalności, dostępne poprzez REST API i interfejs użytkownika:

### Funkcjonalności API (Backend)
* **Zarządzanie produktami spożywczymi (CRUD)**: Pełny zestaw operacji Create, Read, Update, Delete dla obiektów `Product`.
    * Dodawanie nowego produktu spożywczego z nazwą, datą ważności i innymi atrybutami (np. `POST /api/products`).
    * Pobieranie listy wszystkich produktów (`GET /api/products`).
    * Pobieranie szczegółów konkretnego produktu (`GET /api/products/{id}`).
    * Aktualizacja danych istniejącego produktu (`PUT /api/products/{id}`).
    * Usuwanie produktu (`DELETE /api/products/{id}`).
* **Autentykacja i Autoryzacja (JWT)**:
    * Rejestracja nowych użytkowników (`POST /api/auth/register`).
    * Logowanie użytkowników i generowanie tokenów JWT (`POST /api/auth/login`).
    * Zabezpieczanie endpointów API, wymagające uwierzytelnienia (dla użytkowników) lub autoryzacji (dla administratorów).
* **Zarządzanie użytkownikami (tylko dla administratorów)**:
    * Pobieranie listy użytkowników (`GET /api/users`).
    * Możliwość zarządzania rolami użytkowników (planowane).
* **Integracja z zewnętrznymi API (planowane)**:
    * Moduł `openFoodFactsAPI` przewiduje pobieranie szczegółowych danych o produktach z zewnętrznej bazy.

### Funkcjonalności Interfejsu Użytkownika (Frontend)
* **Strona logowania/rejestracji**: Dostęp do aplikacji poprzez uwierzytelnienie (`/`).
* **Strona główna (`/home`)**: Centralne miejsce do przeglądania i zarządzania produktami spożywczymi.
* **Szczegóły produktu (`/food-item/:id`)**: Wyświetlanie i edycja szczegółowych informacji o wybranym produkcie.
* **Zarządzanie użytkownikami (`/users`)**: Interfejs dla administratorów do podglądu i zarządzania użytkownikami.
* **Panel administracyjny (`/admin`)**: Dedykowany widok dla administratorów z dodatkowymi opcjami zarządzania.

<small>[Spis treści](#spis-treści)</small>

---

## Wymagania wstępne
Aby uruchomić projekt, będziesz potrzebować:
* **Docker Desktop**: Do łatwego uruchomienia aplikacji za pomocą obrazów kontenerów i Docker Compose.

Jeśli wolisz uruchomić aplikację z kodu źródłowego (dla deweloperów), będziesz potrzebować również:
* **Java Development Kit (JDK)**: Wersja 17 lub nowsza
* **Apache Maven**: Do zarządzania zależnościami i budowania projektu backendowego.
* **MySQL Database**: Lokalna instancja bazy danych MySQL do przechowywania danych.
* **Node.js**: Do uruchomienia i budowania aplikacji frontendowej Vue.js. Zalecana wersja 14 lub nowsza.
* **Git**: Do klonowania repozytorium.

<small>[Spis treści](#spis-treści)</small>

---

## Instrukcja instalacji i uruchomienia

### Klonowanie repozytorium
Użyj komendy: `git clone https://github.com/TwojaNazwaUzytkownika/FoodToEat.git`
Następnie przejdź do katalogu projektu: `cd FoodToEat`

### Uruchomienie aplikacji za pomocą Docker Compose (zalecane)
To najprostszy sposób na uruchomienie całej aplikacji (backend + frontend + baza danych MySQL) za pomocą jednego polecenia.
1.  Upewnij się, że masz zainstalowany **Docker Desktop** (zawiera Docker Compose).
2.  Upewnij się, że lokalny wolumen `fte-db-vol-PROD` jest utworzony, ponieważ `docker-compose.yml` używa go jako zewnętrznego. Jeśli nie, możesz go stworzyć: `docker volume create fte-db-vol-PROD`.
3.  Przejdź do głównego katalogu projektu, gdzie znajduje się plik `docker-compose.yml`: `cd FoodToEat`
4.  Uruchom aplikację: `docker-compose up -d`
    * Aplikacja backendowa będzie dostępna pod adresem `http://localhost:8081`
    * Aplikacja frontendowa będzie dostępna pod adresem `http://localhost:8080`

### Uruchomienie aplikacji z kodu źródłowego (dla deweloperów)

Jeśli chcesz uruchomić poszczególne komponenty z kodu źródłowego, postępuj zgodnie z poniższymi instrukcjami:

#### Konfiguracja bazy danych (MySQL)
1.  Upewnij się, że masz uruchomioną instancję MySQL.
2.  Utwórz nową bazę danych MySQL (np. `fresh_food`).
3.  Zaktualizuj plik `src/main/resources/application.properties` w projekcie Spring Boot (`backend` folder), podając dane dostępowe do swojej bazy danych:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/fresh_food?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
    spring.datasource.username=root
    spring.datasource.password=polki
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.jpa.hibernate.ddl-auto=update
    ```

#### Uruchomienie Backendu (Spring Boot)
1.  Przejdź do katalogu projektu backendowego: `cd FoodToEat/backend`
2.  Zbuduj projekt za pomocą Mavena: `mvn clean install`
3.  Uruchom aplikację Spring Boot: `mvn spring-boot:run`  
    Aplikacja backendowa będzie dostępna domyślnie pod adresem `http://localhost:8081` (zgodnie z `application.properties`).

#### Uruchomienie Frontendu (Vue.js)
1.  Przejdź do katalogu projektu frontendowego: `cd FoodToEat/frontend`
2.  Zainstaluj zależności Node.js: `npm install`
3.  Uruchom aplikację Vue.js w trybie deweloperskim: `npm run serve`  
    Aplikacja frontendowa będzie dostępna domyślnie pod adresem `http://localhost:8080` (ustawione w `vue.config.js` poprzez proxy).

<small>[Spis treści](#spis-treści)</small>

---

## Sposób użycia
Po uruchomieniu aplikacji (za pomocą Docker Compose lub z kodu źródłowego):
1.  Otwórz przeglądarkę i przejdź do adresu aplikacji frontendowej (np. `http://localhost:8080`).
2.  Zostaniesz przekierowany na stronę logowania/rejestracji. Użyj danych logowania dla użytkownika lub administratora (szczegóły poniżej w sekcji "Dane logowania aplikacji") lub zarejestruj nowe konto.
3.  Po zalogowaniu, interfejs użytkownika pozwoli Ci na dodawanie, przeglądanie, edytowanie i usuwanie produktów spożywczych, a także zarządzanie użytkownikami (jeśli jesteś administratorem).
4.  Dostęp do interaktywnej dokumentacji API (Swagger UI) dla endpointów backendowych będzie możliwy pod adresem: `http://localhost:8081/swagger-ui.html`.

<small>[Spis treści](#spis-treści)</small>

---

## Użyte technologie
Projekt `Waste no food` wykorzystuje następujące kluczowe technologie i narzędzia:

### Backend
* **Java 17**: Podstawowy język programowania, z wykorzystaniem nowoczesnych funkcji języka.
* **Spring Boot 3.3.0**: Framework do szybkiego tworzenia aplikacji opartych na Javie, zapewniający stabilność i bogactwo funkcji.
* **Spring Data JPA / Hibernate**: Do obsługi bazy danych, zarządzania encjami i mapowania obiektowo-relacyjnego.
* **Spring Security & JWT**: Kompleksowy mechanizm autentykacji i autoryzacji z wykorzystaniem JSON Web Tokens do zabezpieczania REST API.
* **Lombok**: Upraszcza generowanie kodu boilerplate (gettery, settery, konstruktory itp.), zwiększając czytelność kodu.
* **MapStruct**: Procesor do generowania mapowań między obiektami DTO a encjami, redukując manualną pracę.
* **Spring Boot Starter Validation**: Do łatwej walidacji danych wejściowych w API.

### Baza Danych
* **MySQL**: Relacyjna baza danych, wykorzystywana do trwałego przechowywania danych aplikacji.

### Testowanie
* **JUnit 5**: Standardowy framework do pisania i uruchamiania testów jednostkowych.
* **Mockito**: Biblioteka do tworzenia obiektów mockowych, ułatwiająca testowanie izolowanych komponentów.
* **JaCoCo**: Narzędzie do analizy pokrycia kodu testami, pomagające monitorować jakość testów.

### Narzędzia Deweloperskie
* **IntelliJ IDEA**: Zintegrowane środowisko programistyczne (IDE), używane do rozwoju projektu.
* **Git**: Rozproszony system kontroli wersji.
* **Postman**: Narzędzie do testowania, dokumentowania i monitorowania API.
* **OpenAPI (Swagger UI)**: Do automatycznego generowania interaktywnej dokumentacji REST API, dostępnej pod `/swagger-ui.html`.

### Frontend
* **Vue.js 3**: Progresywny framework JavaScript do budowy dynamicznych i responsywnych interfejsów użytkownika.
* **Pinia**: Lekki i intuicyjny magazyn stanu dla aplikacji Vue.js, następca Vuex.
* **Vue Router**: Oficjalny router dla Vue.js, do zarządzania nawigacją w aplikacji jednostronicowej.
* **Bootstrap**: Popularny framework CSS, używany do szybkiego tworzenia responsywnych i estetycznych interfejsów.
* **Node.js & npm/Yarn**: Środowisko uruchomieniowe JavaScript i menedżery pakietów do zarządzania zależnościami frontendowymi.

### DevOps / Deployment
* **Docker**: Technologia konteneryzacji, umożliwiająca pakowanie aplikacji i jej zależności w przenośne kontenery.
* **Docker Compose**: Narzędzie do definiowania i uruchamiania wielokontenerowych aplikacji Dockerowych, ułatwiające orkiestrację środowiska.
* **Buildpacks.io / Google Jib (Maven Plugins)**: Narzędzia do automatycznego tworzenia obrazów Dockerowych dla aplikacji Spring Boot bezpośrednio z Mavena (planowane).
* **GitHub Actions**: Do automatyzacji procesów Continuous Integration/Continuous Deployment (CI/CD) (planowane).

<small>[Spis treści](#spis-treści)</small>

---

## Status projektu
Projekt `Waste no food` jest w **aktywnej fazie rozwoju (W toku)**. Jest to projekt treningowy, co oznacza, że jego głównym celem jest nauka i eksperymentowanie z nowymi technologiami i wzorcami projektowymi. Nowe funkcjonalności są sukcesywnie dodawane, a istniejące ulepszane i optymalizowane.

---

## Licencja
Projekt jest prywatny i nie jest przeznaczony do publicznego użytku ani modyfikacji bez zgody autora.

<small>[Spis treści](#spis-treści)</small>

---

## Zrzuty ekranu lub demo
................

<small>[Spis treści](#spis-treści)</small>

---

## Lista zadań
Poniżej znajduje się lista planowanych i zrealizowanych zadań dla projektu:

* **Backend (API & Dane)**
    * [x] Konfiguracja bazy danych MySQL i warstwy dostępu do danych (JPA/Hibernate).
    * [x] Implementacja podstawowego REST API dla operacji CRUD na produktach spożywczych.
    * [x] Implementacja autentykacji i autoryzacji opartej na Spring Security i JWT.
    * [ ] Dodanie zaawansowanego logowania i monitorowania (np. Logback/SLF4J).
    * [ ] Pokrycie kodu backendu kompleksowymi testami jednostkowymi i integracyjnymi.
    * [ ] Integracja z zewnętrznym API (np. Open Food Facts API) w celu automatycznego pobierania danych o produktach.
* **Frontend (Interfejs Użytkownika)**
    * [x] Rozwój interfejsu użytkownika w Vue.js z Vue Router i Pinia.
    * [x] Strona logowania i rejestracji.
    * [x] Widok listy produktów spożywczych.
    * [x] Formularze do dodawania i edycji produktów.
    * [x] Widoki zarządzania użytkownikami i panelu administracyjnego.
    * [ ] Obsługa błędów i walidacja danych na poziomie UI, zapewniająca lepsze doświadczenia użytkownika.
    * [ ] Implementacja zaawansowanych funkcji UI (np. wyszukiwanie, filtrowanie, sortowanie, paginacja).
* **Deployment & DevOps**
    * [x] Przygotowanie konfiguracji Docker Compose dla łatwego uruchamiania całej aplikacji.
    * [ ] Konfiguracja Continuous Integration/Continuous Deployment (CI/CD) przy użyciu GitHub Actions.
    * [ ] Wdrożenie aplikacji na platformie chmurowej (np. AWS, Oracle Cloud).
    * [ ] Optymalizacja obrazów Dockerowych dla środowiska produkcyjnego.
* **Dokumentacja**
    * [x] Aktualizacja dokumentacji README.md (niniejszy plik).
    * [ ] Rozszerzenie dokumentacji API (Swagger) o szczegółowe opisy endpointów i modeli.

<small>[Spis treści](#spis-treści)</small>

---

## Kontakt lub współpraca
Jeśli masz pytania, sugestie lub chciałbyś nawiązać współpracę, skontaktuj się ze mną:

**Paweł Bugiel**
* LinkedIn: [https://www.linkedin.com/in/pawel-bugiel-b936a1229](https://www.linkedin.com/in/pawel-bugiel-b936a1229) 
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

<p align="center"><a href="#top-of-this-page">^ Wróć na górę strony ^</a></p>