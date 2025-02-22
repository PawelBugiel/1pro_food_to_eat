# 1. Użycie obrazu bazowego z Node.js
FROM node:18 AS build-stage

# 2. Ustawienie katalogu roboczego
WORKDIR /app

# 3. Kopiowanie plików projektu do kontenera
COPY . .

# 4. Instalacja zależności
RUN npm install

# 5. Zbudowanie aplikacji frontendowej
RUN npm run build

# 6. Użycie obrazu serwera Nginx do serwowania aplikacji
FROM nginx:alpine AS production-stage

# 7. Kopiowanie wynikowych plików do Nginx
COPY --from=build-stage /app/dist /usr/share/nginx/html

# 8. Ekspozycja portu (np. 80)
EXPOSE 80

# 9. Uruchomienie serwera Nginx
CMD ["nginx", "-g", "daemon off;"]