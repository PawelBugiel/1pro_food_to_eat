<template>
  <div class="container mt-4">
    <h1 class="mb-4">Products list</h1>

    <!-- Wybór parametru sortowania -->

    <div>
      <label for="sortBy">Sort by:</label>
      <select v-model="sortBy" id="sortBy">
        <option value="name">Name</option>
        <option value="quantity">Quantity</option>
        <option value="expiryDate">Expiry date</option>
      </select>
    </div>

    <!-- Wybór kierunku sortowania -->

    <div>
      <label for="sortDirection">Sort direction:</label>
      <select v-model="sortDirection" id="sortDirection">
        <option value="asc">Ascending</option>
        <option value="desc">Descending</option>
      </select>
    </div>

    <div v-if="error" class="alert alert-danger">
      {{ error }}
    </div>

    <table class="table table-bordered table-striped" v-if="!error">
      <thead>
        <tr>
          <th>Product name</th>
          <th>Quantity</th>
          <th>Expiry date</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="product in products" :key="product.id">
          <td>{{ product.name }}</td>
          <td>{{ product.quantity }}</td>
          <td>{{ product.expiryDate }}</td>
        </tr>
      </tbody>
    </table>

    <!-- Przyciski paginacji -->

    <div class="d-flex justify-content-between mt-3">
      <button @click="prevPage" :disabled="currentPage === 0">Previous page</button>
      <button @click="nextPage" :disabled="!hasMoreProducts">Next page</button>
    </div>

    <!-- Informacja o braku wyników -->

    <div v-if="!hasMoreProducts && !error" class="alert alert-info mt-3">
      Nie ma więcej wyników.
    </div>
  </div>
</template>


<script>
import axios from '../axios';                   // Upewnij się, że ścieżka do axios jest poprawna

export default {
  data() {
    return {
       products: [],
          currentPage: 0,                   // Numer bieżącej strony
          error: null,                       // Zmienna do przechowywania komunikatów o błędach
          sortBy: 'expiryDate',                    // Domyślny parametr sortowania
          sortDirection: 'asc',               // Domyślny kierunek sortowania
          hasMoreProducts: true             // Zmienna do przechowywania informacji o dostępności kolejnych wyników
    };
  },
  mounted() {
    this.fetchProducts();
  },
  methods: {
 async fetchProducts() {
   try {
     const response = await axios.get('/products', {
       params: {
         page: this.currentPage,
         sortBy: this.sortBy,
         sortDirection: this.sortDirection.toUpperCase()                                        // Użycie wielkich liter
       }
     });

     console.log("Odpowiedź z backendu:", response.data);                                       // Logowanie odpowiedzi

     this.products = response.data.content;                                                  // Zakładamy, że backend zwraca listę produktów w 'content'
     this.hasMoreProducts = response.data.totalPages > this.currentPage + 1;                // Sprawdzenie, czy są dostępne kolejne strony
     console.log("Has More Products:", this.hasMoreProducts);                               // Logowanie stanu hasMoreProducts
     console.log("Liczba dostępnych stron:", response.data.totalPages);                     // Logowanie totalPages

     this.error = null;                                                                     // Resetujemy błąd, jeśli zapytanie zakończyło się pomyślnie
   } catch (error) {
     console.error("Błąd podczas pobierania produktów:", error);
     this.error = 'Nie udało się załadować produktów. Sprawdź, czy serwer jest uruchomiony.';
     this.hasMoreProducts = false;                                                      // Ustaw hasMoreProducts na false, gdy wystąpił błąd
   }
 },




    // Metoda do zmiany na poprzednią stronę
        prevPage() {
          if (this.currentPage > 0) {
            this.currentPage--;
            this.fetchProducts();
          }
        },
        // Metoda do zmiany na następną stronę
        nextPage() {
          this.currentPage++;
          this.fetchProducts();
        }
  }
}</script>

<style scoped>
/* Opcjonalne stylowanie */
button {
  margin: 10px;
}
</style>