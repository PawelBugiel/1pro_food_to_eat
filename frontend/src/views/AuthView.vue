<template>
  <div class="auth-container">
    <h2>Welcome</h2>

    <form @submit.prevent="handleLogin">
      <div class="mb-3">
        <label for="email" class="form-label">Email:</label>
        <input v-model="formData.email" type="email" id="email" class="form-control form-control-sm" required />
      </div>
      <div class="mb-3">
        <label for="password" class="form-label">Password:</label>
        <input v-model="formData.password" type="password" id="password" class="form-control form-control-sm" required />
      </div>

      <button type="submit" class="btn btn-info btn-compact btn-login">Login</button>
    </form>

    <p v-if="error" class="text-danger mt-2">{{ error }}</p>
  </div>
</template>

<script>
// ... (sekcja script pozostaje bez zmian) ...
import axios from '@/axios.js';
import { useAuthStore } from '@/stores/authStore';

export default {
  data() {
    return {
      formData: {
        email: '',
        password: ''
      },
      error: ''
    };
  },
  methods: {
    async handleLogin() {
      try {
        console.log('Sending login request with:', this.formData);
        const response = await axios.post('/auth/login', {
          email: this.formData.email,
          password: this.formData.password
        });
        console.log('Login response:', response.data);
        const { token } = response.data;

        // Zapisz token w Pinia
        const authStore = useAuthStore();
        authStore.setToken(token);

        // Przekierowanie w zależności od roli
        if (authStore.role === 'ADMIN') {
          this.$router.push('/admin');
        } else {
          this.$router.push('/home');
        }
      } catch (err) {
        console.error('Login error:', err);
        this.error = 'Login failed. Please check your credentials.';
        if (err.response) {
          this.error += ` (Status: ${err.response.status}, Message: ${err.response.data.message || err.response.statusText})`;
        }
      }
    }
  }
};
</script>

<style scoped>
.auth-container {
  max-width: 400px;
  margin: 50px auto;
  padding: 20px;
}
</style>