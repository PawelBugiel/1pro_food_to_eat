import axios from 'axios';

const instance = axios.create({
  baseURL: 'http://192.168.0.172:8081/api',  // Adres backendu (Spring Boot API)
  headers: {
    'Content-Type': 'application/json'
  }
});

export default instance;
