# Bazowy obraz Node.js do budowania aplikacji Vue.js
FROM node:18 AS build

# Ustaw katalog roboczy
WORKDIR /app

# Skopiuj pliki package.json i package-lock.json
COPY package*.json ./

# Zainstaluj zależności
RUN npm install

# Skopiuj resztę kodu źródłowego
COPY . .

# Zbuduj aplikację Vue.js
RUN npm run build

# Użyj lekkiego obrazu Nginx do serwowania aplikacji
FROM nginx:alpine AS production

# Skopiuj zbudowane pliki z etapu budowania
COPY --from=build /app/dist /usr/share/nginx/html

# Otwórz port 80
EXPOSE 80

# Uruchom Nginx
CMD ["nginx", "-g", "daemon off;"]