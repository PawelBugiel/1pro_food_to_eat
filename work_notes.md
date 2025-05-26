CREATE  
2. create product - name :  “A onion” nie dziła (relates to update)

2. search by partial name - BE sends requests to DB for each entered letter.

3. update product - name :  “A onion”  works   (relates to create )

4. go to update form ( page top) if you want to update product - to avoid scrolling by user

5. pagination change QueryParam to Page, Pageable

6. logging to a file / console

7. error handling - handle BE not responding exception

8. default sorting in BE or FE ?

9. Documentation - consider javadoc, Springdoc OpenAPI Swagger

10. trimming request values

----------------------------------
5-11-2025  
BUG  
Update product:
HTTP. Status 400 – Bad Request
test through postman failed to convert value of type 'java.lang.String' to required type 'java.util.UUID'; Invalid UUID string: <uuid>
----------------------------------

5-11-2025  
REFINE  
authStore.js  
Bezpieczeństwo localStorage:  
Przechowywanie tokenu w localStorage jest powszechne, ale naraża token na ataki XSS (Cross-Site Scripting). Jeśli atakujący wstrzyknie złośliwy skrypt, może odczytać token z localStorage.
    Alternatywą jest przechowywanie tokenu w HttpOnly cookie, co zabezpiecza przed XSS, ale wymaga zmian w backendzie (ustawienie cookie przez serwer).

Walidacja tokenu:  
Kod nie sprawdza ważności tokenu (np. czy nie wygasł). Jeśli token wygaśnie, żądania do API będą odrzucane (np. status 401 Unauthorized). Możesz dodać logikę sprawdzającą exp (expiration) z dekodowanego tokenu i automatyczne wylogowanie użytkownika, jeśli token wygasł.

Domyślna rola:  
Ustawianie "ENDUSER" jako domyślnej roli może być problematyczne, jeśli API wymaga konkretnej roli. Warto rozważyć wylogowanie użytkownika, jeśli rola nie jest dostępna.
----------------------------------

25.05.2025  
REFINE  
User management - n + 1 problem

----------------------------------