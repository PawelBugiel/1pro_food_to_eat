<template>
  <div class="container mt-4">
    <div class="d-flex justify-content-between align-items-center mb-4">
      <router-link to="/home" class="btn btn-info btn-sm btn-compact btn-go-to-products">Go to Products</router-link>
      <button @click="logout" class="btn btn-info btn-sm btn-compact btn-logout">Logout</button>
    </div>

    <h2>User management</h2>

    <div class="p-2 mb-3 col-md-6 col-sm-12 mx-auto">
      <form @submit.prevent="registerUser">
        <div class="mb-2">
          <label for="register-email" class="form-label">Email:</label>
          <input v-model="newUser.email" type="email" id="register-email" class="form-control form-control-sm" required/>
        </div>
        <div class="mb-2">
          <label for="register-password" class="form-label">Password:</label>
          <input v-model="newUser.password" type="password" id="register-password" class="form-control form-control-sm" required/>
        </div>
        <div class="mb-2">
          <label for="register-role" class="form-label">Role:</label>
          <select v-model="newUser.role" id="register-role" class="form-select form-select-sm">
            <option value="ENDUSER">End user</option>
            <option value="ADMIN">Administrator</option>
          </select>
        </div>
        <button type="submit" class="btn btn-custom-register btn-sm">Register new user</button>
      </form>
      <p v-if="registerError" class="text-danger mt-2">{{ registerError }}</p>
    </div>

    <div v-if="error" class="alert alert-danger">
      {{ error }}
    </div>

    <table class="table table-bordered table-striped" v-if="users.length">
      <thead>
      <tr>
        <th>Email</th>
        <th>Role</th>
        <th>Actions</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="user in users" :key="user.id">
        <td>{{ user.email }}</td>
        <td>{{ user.roles && user.roles.length > 0 ? user.roles[0].name.replace("ROLE_", "") : "NO_ROLE" }}</td>
        <td>
          <button @click="deleteUser(user.id)" class="btn btn-custom-delete btn-sm">Delete</button>
        </td>
      </tr>
      </tbody>
    </table>
    <p v-if="!users.length && !error" class="text-center">No users found.</p>
  </div>
</template>

<script>

import axios from '@/axios.js';
import {useAuthStore} from '@/stores/authStore';

export default {
  setup() {
    const authStore = useAuthStore();
    return {authStore};
  },
  data() {
    return {
      users: [],
      newUser: {
        email: '',
        password: '',
        role: 'ENDUSER'
      },
      error: '',
      registerError: ''
    };
  },
  mounted() {
    this.fetchUsers();
  },
  methods: {
    async fetchUsers() {
      try {
        const response = await axios.get('/auth/users', {
          headers: {
            Authorization: `Bearer ${this.authStore.token}`
          }
        });
        this.users = response.data;
      } catch (err) {
        this.error = 'Failed to load users. Please check your authentication.';
      }
    },
    async registerUser() {
      try {
        const endpoint = this.newUser.role === 'ADMIN' ? '/auth/register-admin' : '/auth/register-enduser';
        await axios.post(endpoint, {
          email: this.newUser.email,
          password: this.newUser.password,
          role: this.newUser.role
        }, {
          headers: {
            Authorization: `Bearer ${this.authStore.token}`
          }
        });
        this.registerError = '';
        this.newUser = {email: '', password: '', role: 'ENDUSER'};
        this.fetchUsers();
      } catch (err) {
        this.registerError = 'Registration failed. Check your permissions or input.';
      }
    },
    async deleteUser(userId) {
      if (confirm(`Are you sure you want to delete user with ID ${userId}?`)) {
        try {
          await axios.delete(`/auth/user/${userId}`, {
            headers: {
              Authorization: `Bearer ${this.authStore.token}`
            }
          });
          this.fetchUsers();
        } catch (err) {
          this.error = 'Failed to delete user. Check your permissions.';
        }
      }
    },
    logout() {
      this.authStore.clearAuth();
      this.$router.push('/');
    }
  }
};
</script>