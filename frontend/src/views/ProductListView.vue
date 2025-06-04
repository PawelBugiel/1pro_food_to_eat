<template>
  <div class="container mt-4">
    <div class="d-flex justify-content-between align-items-center mb-4">
      <div>
        <router-link v-if="authStore.role === 'ADMIN'" to="/users" class="btn btn-info btn-sm">Manage Users</router-link>
      </div>
      <div>
        <button @click="logout" class="btn btn-info btn-sm">Logout</button>
      </div>
    </div>

    <h1 class="mb-4">Products list</h1>

    <form @submit.prevent="isEditMode ? updateProduct() : addProduct()" class="mb-4">
      <div class="row align-items-center">
        <div class="col">
          <input v-model="newProduct.name" type="text" class="form-control" placeholder="Product name" required />
        </div>
        <div class="col">
          <input v-model.number="newProduct.quantity" type="number" class="form-control" placeholder="Quantity" required />
        </div>
        <div class="col">
          <input v-model="newProduct.expiryDate" type="date" class="form-control" required />
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
          <input v-model="searchQuery" type="text" class="form-control" placeholder="Search by name" @input="fetchProducts" />
        </div>
      </div>
    </div>

    <table class="table table-bordered table-striped">
      <thead>
      <tr>
        <th>
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
        <th>Actions</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="product in products" :key="product.id">
        <td>{{ product.name }}</td>
        <td>{{ product.quantity }}</td>
        <td :class="{ 'text-danger': isExpired(product.expiryDate) }">
          {{ product.expiryDate }}
        </td>
        <td>
          <button @click="editProduct(product)" class="btn btn-sm btn-custom-edit-update mx-1">Edit</button>
          <button @click="showDeleteModal(product)" class="btn btn-sm btn-custom-delete">Delete</button>
        </td>
      </tr>
      </tbody>
    </table>

    <div class="d-flex justify-content-between mb-4">
      <button @click="currentPage--" :disabled="currentPage === 0" class="btn btn-secondary">Previous</button>
      <button @click="currentPage++" :disabled="!hasMoreProducts" class="btn btn-secondary">Next</button>
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
      searchQuery: ''
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
        // Synchronizuj dane z formularza
        this.currentProduct = { ...this.newProduct, id: this.currentProduct.id };
        await axios.put(`/products/${this.currentProduct.id}`, this.currentProduct, {
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
    editProduct(product) {
      this.isEditMode = true;
      this.currentProduct = { ...product };
      this.newProduct = { ...product };
    },
    cancelEdit() {
      this.isEditMode = false;
      this.currentProduct = { id: '', name: '', quantity: null, expiryDate: '' };
      this.newProduct = { name: '', quantity: null, expiryDate: '' };
    },
    showDeleteModal(product) {
      this.productToDelete = product;
      this.deleteModal.show();
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
    }
  }
};
</script>