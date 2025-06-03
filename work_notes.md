# Spis Treści

* [BE-BUG-01-01-2025: Create product](#be-bug-01-01-2025-create-product)
* [BE-REFINE-01-01-2025: Search by partial name](#be-refine-01-01-2025-search-by-partial-name)
* [BE-BUG-01-01-2025: Update product (ponownie)](#be-bug-01-01-2025-update-product-ponownie)
* [FE-REFINE-01-01-2025: Go to update form](#fe-refine-01-01-2025-go-to-update-form)
* [BE-REFINE-01-01-2025: Pagination](#be-refine-01-01-2025-pagination)
* [BE-REFINE-01-01-2025: Logging](#be-refine-01-01-2025:-logging)
* [BE-BUG-01-01-2025: Error handling](#be-bug-01-01-2025-error-handling)
* [BE/FE-REFINE-01-01-2025: Domyślne sortowanie](#befe-refine-01-01-2025-domyślne-sortowanie)
* [BE-REFINE-01-01-2025: Documentation](#be-refine-01-01-2025-documentation)
* [BE-REFINE-01-01-2025: Trimming request values](#be-refine-01-01-2025-trimming-request-values)
* [BE-BUG-11-05-2025: Update product](#be-bug-11-05-2025-update-product)
* [BE-REFINE-SECURITY-11-05-2025: authStore.js](#be-refine-security-11-05-2025-authstorejs)
* [BE-REFINE-OPTIMALIZATION-25.05.2025: User management](#be-refine-optimalization-25052025-user-management)
* [DEV-FEATURE-26.05.2025: Profile separation](#dev-feature-26052025-profile-separation)
* [BE-REFINE-SECURITY-28-05-2025: Database credentials](#be-refine-security-28-05-2025-database-credentials)
* [FE/BE-BUG-03-06-2025: Add new product](#febe-bug-03-06-2025-add-new-product)

---

## BE-BUG-01-01-2025: Create product

* **create product** - nazwa: "A onion" nie działa (powiązane z aktualizacją).

*<sub>[Wróć do Spisu Treści](#spis-treści)</sub>*

---

## BE-REFINE-01-01-2025: Search by partial name

* **search by partial name** - BE wysyła zapytania do DB dla każdej wprowadzonej litery.

*<sub>[Wróć do Spisu Treści](#spis-treści)</sub>*

---

## BE-BUG-01-01-2025: Update product (ponownie)

* **update product** - nazwa: "A onion" działa (powiązane z tworzeniem).

*<sub>[Wróć do Spisu Treści](#spis-treści)</sub>*

---

## FE-REFINE-01-01-2025: Go to update form

* **go to update form** (góra strony), jeśli chcesz zaktualizować produkt - aby uniknąć przewijania przez użytkownika.

*<sub>[Wróć do Spisu Treści](#spis-treści)</sub>*

---

## BE-REFINE-01-01-2025: Pagination

* **pagination** - zmień QueryParam na Page, Pageable.

*<sub>[Wróć do Spisu Treści](#spis-treści)</sub>*

---

## BE-REFINE-01-01-2025: Logging

* **logging** - do pliku / konsoli.

*<sub>[Wróć do Spisu Treści](#spis-treści)</sub>*

---

## BE-BUG-01-01-2025: Error handling

* **error handling** - obsługa wyjątku braku odpowiedzi z BE.

*<sub>[Wróć do Spisu Treści](#spis-treści)</sub>*

---

## BE/FE-REFINE-01-01-2025: Domyślne sortowanie

* **default sorting** - w BE czy FE?

*<sub>[Wróć do Spisu Treści](#spis-treści)</sub>*

---

## BE-REFINE-01-01-2025: Documentation

* **Documentation** - rozważ Javadoc, Springdoc OpenAPI Swagger.

*<sub>[Wróć do Spisu Treści](#spis-treści)</sub>*

---

## BE-REFINE-01-01-2025: Trimming request values

* **Trimming request values**.

*<sub>[Wróć do Spisu Treści](#spis-treści)</sub>*

---

## BE-BUG-11-05-2025: Update product

**HTTP Status 400 – Bad Request**
Test przez Postmana nie powiódł się: problem z konwersją wartości typu 'java.lang.String' na wymagany typ 'java.util.UUID'; Nieprawidłowy ciąg UUID: `<uuid>`.

*<sub>[Wróć do Spisu Treści](#spis-treści)</sub>*

---

## BE-REFINE-SECURITY-11-05-2025: authStore.js

* **Bezpieczeństwo localStorage:** Przechowywanie tokenu w localStorage jest powszechne, ale naraża token na ataki XSS (Cross-Site Scripting). Jeśli atakujący wstrzyknie złośliwy skrypt, może odczytać token z localStorage.
  * **Alternatywa:** Przechowywanie tokenu w HttpOnly cookie, co zabezpiecza przed XSS, ale wymaga zmian w backendzie (ustawienie cookie przez serwer).
* **Walidacja tokenu:** Kod nie sprawdza ważności tokenu (np. czy nie wygasł). Jeśli token wygaśnie, żądania do API będą odrzucane (np. status 401 Unauthorized). Warto dodać logikę sprawdzającą `exp` (expiration) z dekodowanego tokenu i automatyczne wylogowanie użytkownika, jeśli token wygasł.
* **Domyślna rola:** Ustawianie "ENDUSER" jako domyślnej roli może być problematyczne, jeśli API wymaga konkretnej roli. Warto rozważyć wylogowanie użytkownika, jeśli rola nie jest dostępna.

*<sub>[Wróć do Spisu Treści](#spis-treści)</sub>*

---

## BE-REFINE-OPTIMALIZATION-25.05.2025: User management

Problem n+1.

*<sub>[Wróć do Spisu Treści](#spis-treści)</sub>*

---

## DEV-FEATURE-26.05.2025: Profile separation

Stwórz osobne profile dla środowisk produkcyjnego (`prod`) i deweloperskiego (`dev`).

*<sub>[Wróć do Spisu Treści](#spis-treści)</sub>*

---

## BE-REFINE-SECURITY-28-05-2025: Database credentials

Dane dostępowe do bazy danych przenieść z pliku `application.properties` do **zmiennych środowiskowych**. Dzięki temu:
* Możliwa zmiana konfiguracji bez modyfikacji kodu i przebudowywania obrazu Dockera.
* Przechowywanie danych uwierzytelniających poza kodem źródłowym.

*<sub>[Wróć do Spisu Treści](#spis-treści)</sub>*
## FE/BE-BUG-03-06-2025: Add new product

Nie można dodać produktu z polskimi znakami diakrytycznymi.

*<sub>[Wróć do Spisu Treści](#spis-treści)</sub>*