<template>
  <div class="container mt-4">
    <h2>User Management</h2>

    <!-- Formularz rejestracji -->
    <div class="card p-3 mb-3">
      <h3>Add New User</h3>
      <form @submit.prevent="registerUser">
        <div class="mb-2">
          <label for="register-email" class="form-label">Email:</label>
          <input v-model="newUser.email" type="email" id="register-email" class="form-control" required />
        </div>
        <div class="mb-2">
          <label for="register-password" class="form-label">Password:</label>
          <input v-model="newUser.password" type="password" id="register-password" class="form-control" required />
        </div>
        <div class="mb-2">
          <label for="register-role" class="form-label">Role:</label>
          <select v-model="newUser.role" id="register-role" class="form-select">
            <option value="ENDUSER">End User</option>
            <option value="ADMIN">Admin</option>
          </select>
        </div>
        <button type="submit" class="btn btn-success">Register</button>
      </form>
      <p v-if="registerError" class="text-danger mt-2">{{ registerError }}</p>
    </div>

    <!-- Komunikat o błędzie -->
    <div v-if="error" class="alert alert-danger">
      {{ error }}
    </div>

    <!-- Lista użytkowników -->
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
        <td>{{ user.role }}</td>
        <td>
          <button @click="deleteUser(user.id)" class="btn btn-danger btn-sm">Delete</button>
        </td>
      </tr>
      </tbody>
    </table>
    <p v-if="!users.length && !error" class="text-center">No users found.</p>
  </div>
</template>

<script>
import axios from '@/axios.js';

export default {
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
            Authorization: `Bearer ${localStorage.getItem('jwtToken')}`
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
            Authorization: `Bearer ${localStorage.getItem('jwtToken')}`
          }
        });
        this.registerError = '';
        this.newUser = { email: '', password: '', role: 'ENDUSER' };
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
              Authorization: `Bearer ${localStorage.getItem('jwtToken')}`
            }
          });
          this.fetchUsers();
        } catch (err) {
          this.error = 'Failed to delete user. Check your permissions.';
        }
      }
    }
  }
};
</script>

<style scoped>
.container {
  max-width: 800px;
  margin: 50px auto;
  padding: 20px;
}
</style>