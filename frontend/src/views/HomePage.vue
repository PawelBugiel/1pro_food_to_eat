<!--------------------------------- T E M P L A T E --------------------------------------->

<template>
  <div class="container mt-4">
    <h1 class="mb-4">Products list</h1>

    <div class="card p-3 mb-3">
      <h3>{{ isEditMode ? "Edit product" : "Add new product" }}</h3>

      <form @submit.prevent="isEditMode ? updateProduct() : addProduct()">
        <div class="mb-2">
          <label for="name" class="form-label">Product name:</label>
          <input v-model="currentProduct.name" type="text" id="name" class="form-control" required
                 :disabled="isExpiryDatePast"/>
        </div>

        <div class="mb-2">
          <label for="quantity" class="form-label">Quantity:</label>
          <input v-model="currentProduct.quantity" type="number" id="quantity" class="form-control" required
                 :disabled="isExpiryDatePast"/>
        </div>

        <div class="mb-2">
          <label for="expiryDate" class="form-label">Expiry date:</label>
          <input v-model="currentProduct.expiryDate" type="date" id="expiryDate" class="form-control" required/>
        </div>

        <button type="submit" class="btn btn-success">
          {{ isEditMode ? "Update product" : "Add product" }}
        </button>

        <button v-if="isEditMode" @click="cancelEdit" class="btn btn-secondary ms-2">Cancel</button>
      </form>

      <div v-if="productError" class="alert alert-danger mt-2">
        {{ productError }}
      </div>
    </div>

    <!-- Partial name search -->
    <div class="mb-3">
      <label for="search" class="form-label">Search product:</label>
      <input
          v-model="searchQuery"
          @input="fetchProducts"
          type="text"
          id="search"
          class="form-control"
          placeholder="Enter product name"
      />
    </div>

    <div>
      <label for="sortBy">Sort by:</label>
      <select v-model="sortBy" id="sortBy">
        <option value="name">Name</option>
        <option value="quantity">Quantity</option>
        <option value="expiryDate">Expiry date</option>
      </select>
    </div>

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

    <button @click="fetchExpiredProducts" class="btn btn-danger mt-3">
      Show Expired Products
    </button>


    <table class="table table-bordered table-striped" v-if="!error">
      <thead>

      <tr>
        <th>Product name</th>
        <th>Quantity</th>
        <th>Expiry date</th>
        <th>Actions</th>
      </tr>

      </thead>
      <tbody>

      <tr v-for="product in products" :key="product.id">
        <td>{{ product.name }}</td>
        <td>{{ product.quantity }}</td>
        <td>{{ product.expiryDate }}</td>
        <td>
          <button @click="editProduct(product)" class="btn btn-warning btn-sm">Update</button>
          <button @click="openDeleteModal(product)" class="btn btn-danger btn-sm ms-2">Delete</button>
        </td>
      </tr>

      </tbody>
    </table>

    <!-- Pagination -->
    <div class="d-flex justify-content-between mt-3">
      <button @click="prevPage" :disabled="currentPage === 0">Previous page</button>
      <button @click="nextPage" :disabled="!hasMoreProducts">Next page</button>
    </div>

    <div v-if="!hasMoreProducts && !error" class="alert alert-info mt-3">
      There are no more results.
    </div>

    <!-- BOOTSTRAP MODAL-->
    <div class="modal fade" id="deleteModal" tabindex="-1" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">Confirm Deletion</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
            Are you sure you want to delete product "<strong>{{ productToDelete?.name }}</strong>"?
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" @click="closeModal">Cancel</button>
            <button type="button" class="btn btn-danger" @click="deleteProduct()">Delete</button>
          </div>
        </div>
      </div>
    </div>
    <!-- MODAL -->

    <div v-if="productError" class="alert alert-danger mt-2">
      {{ productError }}
    </div>

  </div>
</template>

<!--------------------------------- S C R I P T --------------------------------------->

<script>
import axios from '../axios';
import {Modal} from 'bootstrap';

export default {
  data() {
    return {
      products: [],
      currentPage: 0,
      error: null,
      sortBy: 'expiryDate',
      sortDirection: 'asc',
      hasMoreProducts: true,
      // add product
      newProduct: {name: '', quantity: null, expiryDate: ''},
      addProductError: null,
      // update
      isEditMode: false,
      currentProduct: {id: '', name: '', quantity: null, expiryDate: ''},
      productError: null,
      // delete
      productToDelete: null,
      deleteModal: null,     // Bootstrap Modal
      // find by name
      searchQuery: ''

    };
  },
  mounted() {
    this.fetchProducts();
    this.deleteModal = new Modal(document.getElementById('deleteModal'));   // Modal
  },
  watch: {
    // Watcher refreshes product list after sorting change
    sortBy() {
      this.fetchProducts();
    },
    sortDirection() {
      this.fetchProducts();
    }
  },

  computed: {
    isExpiryDatePast() {
      if (!this.currentProduct.expiryDate) return false; // If date is empty, editing is allowed
      return new Date(this.currentProduct.expiryDate) < new Date(); // Check if the date is in the past
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

        if (this.searchQuery) {  // If the user entered something, we add the `partialName` parameter
          params.partialName = this.searchQuery;
        }

        // The query changes the URL depending on the search
        const response = await axios.get(this.searchQuery ? '/products/partial-name' : '/products', {
          params: params
        });

        console.log("Backend response:", response.data);

        this.products = response.data.content;     // We assume that the backend returns a list of products in 'content'
        this.hasMoreProducts = response.data.totalPages > this.currentPage + 1;   // Check if there are more pages available

        console.log("Has More Products:", this.hasMoreProducts);
        console.log("Available pages quantity:", response.data.totalPages);

        this.error = null;
      } catch (error) {

        console.error("Error while downloading products:", error);

        this.error = 'Failed to load products. Check if the server is running..';
        this.hasMoreProducts = false;
      }
    },

    async addProduct() {
      try {
        const response = await axios.post('/products', this.currentProduct);

        console.log("Product added:", response.data);

        // Form reset
        this.currentProduct = {name: '', quantity: null, expiryDate: ''};
        this.addProductError = null;

        // Refresh product list
        this.fetchProducts();

      } catch (error) {
        console.error("Error while adding product:", error);
        this.addProductError = 'Failed to add product. Please check your input.';
      }
    },

    editProduct(product) {
      this.isEditMode = true;
      this.currentProduct = {...product}; // Copying product data for editing
      this.productError = null;
    },

    async updateProduct() {
      try {
        const response = await axios.put('/products', this.currentProduct, {
          params: {id: this.currentProduct.id}
        });
        console.log("Product updated:", response.data);
        this.isEditMode = false;
        this.currentProduct = {id: '', name: '', quantity: null, expiryDate: ''};
        this.productError = null;
        this.fetchProducts();
      } catch (error) {
        console.error("Error while updating product:", error);
        this.productError = 'Failed to update product. Please check your input.';
      }
    },

    cancelEdit() {
      this.isEditMode = false;
      this.currentProduct = {id: '', name: '', quantity: null, expiryDate: ''};
      this.productError = null;
    },

    // Modal
    openDeleteModal(product) {
      this.productToDelete = product;
      this.deleteModal.show();
    },

    async deleteProduct() {
      try {
        await axios.delete(`/products/id/${this.productToDelete.id}`);
        this.fetchProducts();
        this.deleteModal.hide();
      } catch (error) {
        console.error("Error while removing product:", error);
        alert("Failed to remove product.");
      }
    },

    async fetchExpiredProducts() {
      try {
        const response = await axios.get('/products/expired', {
          params: {
            page: this.currentPage,
            pageSize: 10,  // Możesz później dodać opcję wyboru
            sortBy: this.sortBy,
            sortDirection: this.sortDirection.toUpperCase()
          }
        });

        console.log("Expired products response:", response.data);
        this.products = response.data.content; // Aktualizacja listy produktów
        console.log(this.products);
        this.hasMoreProducts = response.data.totalPages > this.currentPage + 1;
        // this.hasMoreProducts = response.data.totalElements > 0 && response.data.totalPages > this.currentPage + 1;

        this.error = null;

      } catch (error) {
        console.error("Error fetching expired products:", error);
        this.error = "Failed to load expired products. Check the server connection.";
      }
    },


    closeModal() {
      if (this.deleteModal) {
        this.deleteModal.hide();
      }
    },

    prevPage() {
      if (this.currentPage > 0) {
        this.currentPage--;
        this.fetchProducts();
      }
    },

    nextPage() {
      this.currentPage++;
      this.fetchProducts();
    }
  }
}</script>

<!--------------------------------- S T Y L E --------------------------------------->

<style scoped>
button {
  margin: 10px;
}
</style>