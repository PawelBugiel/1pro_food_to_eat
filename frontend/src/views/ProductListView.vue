<template>
  <div class="container mt-4">
    <div class="d-flex justify-content-between align-items-center mb-4">
      <div>
        <router-link v-if="authStore.role === 'ADMIN'" to="/users" class="btn btn-info btn-compact btn-manage-users">Manage Users</router-link>
      </div>
      <div>
        <button @click="logout" class="btn btn-info btn-sm btn-compact btn-logout">Logout</button>
      </div>
    </div>

    <h2 class="mb-4 product-list-heading">Products list</h2>

    <form @submit.prevent="isEditMode ? updateProduct() : addProduct()" class="mb-4">
      <div class="row align-items-center">
        <div class="col">
          <input v-model="newProduct.name" type="text" class="form-control" placeholder="Product name" required/>
        </div>
        <div class="col">
          <input v-model.number="newProduct.quantity" type="number" class="form-control" placeholder="Quantity"
                 required/>
        </div>
        <div class="col">
          <input v-model="newProduct.expiryDate" type="date" class="form-control" required/>
        </div>
        <div class="col">
          <button v-if="!isEditMode" type="submit" class="btn btn-custom-add btn-sm">Add new product</button>
          <button v-if="isEditMode" type="submit" class="btn btn-custom-edit-update btn-sm">Update</button>
          <button v-if="isEditMode" @click="cancelEdit" type="button" class="btn btn-secondary btn-sm">Cancel</button>
        </div>
      </div>
      <p v-if="addProductError" class="text-danger">{{ addProductError }}</p>
    </form>

    <div class="mb-4">
      <div class="row">
        <div class="col col-md-6 col-sm-12">
          <input v-model="searchQuery" type="text" class="form-control" placeholder="Search by name"
                 @input="fetchProducts"/>
        </div>
      </div>
    </div>

    <div class="d-flex justify-content-start mb-3">
      <button
          @click="editSelectedProduct()"
          :disabled="!selectedProduct"
          class="btn btn-sm btn-custom-edit-update mx-1">
        Edit Selected Product
      </button>
      <button
          @click="showDeleteModal(selectedProduct)"
          :disabled="!selectedProduct"
          class="btn btn-sm btn-custom-delete">
        Delete Selected Product
      </button>
    </div>

    <table class="table table-bordered table-striped">
      <thead>
      <tr>
        <th style="width: 50px;">Select</th> <th>
        <a href="#" @click.prevent="sort('name')">Name</a>
        <span v-if="sortBy === 'name'">{{ sortDirection === 'asc' ? '↑' : '↓' }}</span>
      </th>
        <th>
          <a href="#" @click.prevent="sort('quantity')">Quantity</a>
          <span v-if="sortBy === 'quantity'">{{ sortDirection === 'asc' ? '↑' : '↓' }}</span>
        </th>
        <th>
          <a href="#" @click.prevent="sort('expiryDate')">Expiry Date</a>
          <span v-if="sortBy === 'expiryDate'">{{ sortDirection === 'asc' ? '↑' : '↓' }}</span>
        </th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="product in products" :key="product.id"
          @click="selectProduct(product)"
          :class="{ 'table-active': selectedProduct && selectedProduct.id === product.id }"
          style="cursor: pointer;">
        <td>
          <input type="radio" :value="product.id" v-model="selectedProductId" @click.stop="selectProduct(product)">
        </td>
        <td>{{ product.name }}</td>
        <td>{{ product.quantity }}</td>
        <td :class="{ 'text-danger': isExpired(product.expiryDate) }">
          {{ product.expiryDate }}
        </td>
      </tr>
      </tbody>
    </table>

    <div class="d-flex justify-content-between mb-4">
      <button @click="currentPage--" :disabled="currentPage === 0" class="btn btn-info btn-compact btn-previous-page">
        Previous
      </button>
      <button @click="currentPage++" :disabled="!hasMoreProducts" class="btn btn-info btn-compact btn-next-page">
        Next
      </button>
    </div>

    <div v-if="error" class="alert alert-danger">
      {{ error }}
    </div>

    <div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="deleteModalLabel">Confirm Deletion</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
            Are you sure you want to delete {{ productToDelete?.name }}?
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
            <button type="button" class="btn btn-custom-delete" @click="deleteProduct">Delete</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from '../axios';
import { Modal } from 'bootstrap';
import { useAuthStore } from '@/stores/authStore';

export default {
  setup() {
    const authStore = useAuthStore();
    return { authStore };
  },
  data() {
    return {
      products: [],
      currentPage: 0,
      error: null,
      sortBy: 'expiryDate',
      sortDirection: 'asc',
      hasMoreProducts: true,
      newProduct: { name: '', quantity: null, expiryDate: '' },
      addProductError: null,
      isEditMode: false,
      currentProduct: { id: '', name: '', quantity: null, expiryDate: '' },
      productError: null,
      productToDelete: null,
      deleteModal: null,
      searchQuery: '',
      selectedProduct: null, // Przechowuje CAŁY OBIEKT PRODUKTU
      selectedProductId: null // NOWA ZMIENNA: Przechowuje TYLKO ID wybranego produktu, dla v-model radio
    };
  },
  mounted() {
    this.fetchProducts();
    this.deleteModal = new Modal(document.getElementById('deleteModal')); // Inicjalizacja modala
  },
  watch: {
    currentPage() {
      this.fetchProducts();
    },
    sortBy() {
      this.currentPage = 0;
      this.fetchProducts();
    },
    sortDirection() {
      this.currentPage = 0;
      this.fetchProducts();
    },
    // Opcjonalnie: Synchronizacja selectedProduct na podstawie selectedProductId
    // Może być przydatne, jeśli selectedProductId jest zmieniane z zewnątrz lub przez inne interakcje
    selectedProductId(newId) {
      if (newId === null) {
        this.selectedProduct = null;
      } else {
        const foundProduct = this.products.find(p => p.id === newId);
        if (foundProduct) {
          this.selectedProduct = foundProduct;
        }
      }
    }
  },
  methods: {
    async fetchProducts() {
      try {
        let params = {
          page: this.currentPage,
          sortBy: this.sortBy,
          sortDirection: this.sortDirection.toUpperCase()
        };
        if (this.searchQuery) {
          params.partialName = this.searchQuery;
        }
        const response = await axios.get(this.searchQuery ? '/products/search' : '/products', {
          params,
          headers: {
            Authorization: `Bearer ${this.authStore.token}`
          }
        });
        this.products = response.data.content;
        this.hasMoreProducts = response.data.totalPages > this.currentPage + 1;
        this.error = null;
        this.selectedProduct = null; // Resetuj zaznaczenie po odświeżeniu listy
        this.selectedProductId = null; // Resetuj ID po odświeżeniu listy
      } catch (error) {
        this.error = 'Failed to load products. Check if the server is running..';
        this.hasMoreProducts = false;
      }
    },
    async addProduct() {
      try {
        await axios.post('/products', this.newProduct, {
          headers: {
            Authorization: `Bearer ${this.authStore.token}`
          }
        });
        this.newProduct = { name: '', quantity: null, expiryDate: '' };
        this.addProductError = null;
        this.currentPage = 0;
        this.fetchProducts();
      } catch (error) {
        this.addProductError = 'Failed to add product.';
      }
    },
    async updateProduct() {
      try {
        const productIdToUpdate = this.currentProduct.id; // Używamy ID z currentProduct
        await axios.put(`/products/${productIdToUpdate}`, this.newProduct, {
          headers: {
            Authorization: `Bearer ${this.authStore.token}`
          }
        });
        this.isEditMode = false;
        this.currentProduct = { id: '', name: '', quantity: null, expiryDate: '' };
        this.newProduct = { name: '', quantity: null, expiryDate: '' };
        this.addProductError = null;
        this.fetchProducts();
      } catch (error) {
        console.error('Update error:', error.response?.data || error.message);
        this.addProductError = error.response?.data?.message || 'Failed to update product.';
      }
    },
    // Zmieniona metoda editProduct, teraz aktywuje edycję dla selectedProduct
    editSelectedProduct() {
      if (this.selectedProduct) {
        this.isEditMode = true;
        this.currentProduct = { ...this.selectedProduct }; // Ustawiamy currentProduct z selectedProduct
        this.newProduct = { ...this.selectedProduct }; // Wypełniamy formularz danymi z selectedProduct
      }
    },
    cancelEdit() {
      this.isEditMode = false;
      this.currentProduct = { id: '', name: '', quantity: null, expiryDate: '' };
      this.newProduct = { name: '', quantity: null, expiryDate: '' };
      this.selectedProduct = null; // Wyczyść zaznaczenie po anulowaniu edycji
      this.selectedProductId = null; // Wyczyść ID zaznaczonego produktu
    },
    // Metoda showDeleteModal przyjmuje teraz obiekt produktu
    showDeleteModal(product) {
      if (product) { // Upewnij się, że produkt jest wybrany
        this.productToDelete = product;
        this.deleteModal.show();
      }
    },
    async deleteProduct() {
      try {
        await axios.delete(`/products/${this.productToDelete.id}`, {
          headers: {
            Authorization: `Bearer ${this.authStore.token}`
          }
        });
        this.deleteModal.hide();
        this.productToDelete = null;
        this.selectedProduct = null; // Wyczyść zaznaczenie po usunięciu
        this.selectedProductId = null; // Wyczyść ID zaznaczonego produktu
        this.fetchProducts();
      } catch (error) {
        this.error = 'Failed to delete product.';
      }
    },
    sort(field) {
      if (this.sortBy === field) {
        this.sortDirection = this.sortDirection === 'asc' ? 'desc' : 'asc';
      } else {
        this.sortBy = field;
        this.sortDirection = 'asc';
      }
    },
    isExpired(expiryDate) {
      return new Date(expiryDate) < new Date();
    },
    logout() {
      this.authStore.clearAuth();
      this.$router.push('/');
    },
    // NOWA METODA: Do zaznaczania/odznaczania produktu
    selectProduct(product) {
      if (this.selectedProduct && this.selectedProduct.id === product.id) {
        // Jeśli ten sam produkt jest kliknięty ponownie, odznacz go
        this.selectedProduct = null;
        this.selectedProductId = null;
      } else {
        // Zaznacz nowy produkt
        this.selectedProduct = product;
        this.selectedProductId = product.id;
      }
    }
  }
};
</script>

<style scoped>
/* Dodaj styl dla zaznaczonego wiersza, aby był bardziej widoczny */
.table-active {
  background-color: #e0f2f1 !important; /* Jaśniejszy odcień koloru #54A498 */
  font-weight: bold;
}
</style>