<template>
  <div class="container mt-4">
    <h1 class="mb-4">Lista produktów</h1>
    <div v-if="error" class="alert alert-danger">
      {{ error }}
    </div>
    <table class="table table-bordered table-striped" v-if="!error">
      <thead>
        <tr>
          <th>Nazwa produktu</th>
          <th>Ilość</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="product in products" :key="product.id">
          <td>{{ product.name }}</td>
          <td>{{ product.quantity }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import axios from '../axios'; // Upewnij się, że ścieżka do axios jest poprawna

export default {
  data() {
    return {
      products: [],
      error: null // Zmienna do przechowywania komunikatu o błędzie
    };
  },
  mounted() {
    this.fetchProducts();
  },
  methods: {
    async fetchProducts() {
      try {
        const response = await axios.get('/products');
        this.products = response.data; // Upewnij się, że odpowiada na to, co zwraca backend
        this.error = null; // Resetujemy błąd, jeśli zapytanie zakończyło się pomyślnie
      } catch (error) {
        console.error(error);
        this.error = 'Nie udało się załadować produktów. Sprawdź, czy serwer jest uruchomiony.'; // Ustawiamy komunikat o błędzie
      }
    }
  }
}</script>
