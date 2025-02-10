<template>
  <div class="container mt-4">
    <h1 class="mb-4">Products list</h1>

    <!-- NOWOŚĆ: Formularz dodawania nowego produktu -->
    <div class="card p-3 mb-3">
      <h3>Add new product</h3>
      <form @submit.prevent="addProduct">
        <div class="mb-2">
          <label for="name" class="form-label">Product name:</label>
          <input v-model="newProduct.name" type="text" id="name" class="form-control" required/>
        </div>
        <div class="mb-2">
          <label for="quantity" class="form-label">Quantity:</label>
          <input v-model="newProduct.quantity" type="number" id="quantity" class="form-control" required/>
        </div>
        <div class="mb-2">
          <label for="expiryDate" class="form-label">Expiry date:</label>
          <input v-model="newProduct.expiryDate" type="date" id="expiryDate" class="form-control" required/>
        </div>
        <button type="submit" class="btn btn-success">Add product</button>
      </form>
      <div v-if="addProductError" class="alert alert-danger mt-2">
        {{ addProductError }}
      </div>
    </div>
    <!-- KONIEC: Formularz dodawania nowego produktu -->

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
      hasMoreProducts: true,             // Zmienna do przechowywania informacji o dostępności kolejnych wyników
      // NOWOŚĆ: Dane nowego produktu
      newProduct: {name: '', quantity: null, expiryDate: ''},
      addProductError: null
    };
  },
  mounted() {
    this.fetchProducts();
  },
  watch: {
    // NOWOŚĆ: Watcher odświeża listę produktów po zmianie sortowania
    sortBy() {
      this.fetchProducts();
    },
    sortDirection() {
      this.fetchProducts();
    }
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


    // NOWOŚĆ: Obsługa dodawania nowego produktu
    async addProduct() {
      try {
        const response = await axios.post('/products', this.newProduct);
        console.log("Produkt dodany:", response.data);

        // Reset formularza
        this.newProduct = {name: '', quantity: null, expiryDate: ''};
        this.addProductError = null;

        // Odśwież listę produktów
        this.fetchProducts();
      } catch (error) {
        console.error("Błąd podczas dodawania produktu:", error);
        this.addProductError = 'Nie udało się dodać produktu. Sprawdź dane wejściowe.';
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