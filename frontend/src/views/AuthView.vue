<template>
  <div class="auth-container">
    <h2>Welcome</h2>

    <!-- Przełącznik z przyciskami -->
    <div class="mb-4">
      <button
          class="btn me-2"
          :class="activeTab === 'login' ? 'btn-primary' : 'btn-outline-primary'"
          @click="activeTab = 'login'"
      >
        Login
      </button>
      <button
          class="btn"
          :class="activeTab === 'register' ? 'btn-success' : 'btn-outline-success'"
          @click="activeTab = 'register'"
      >
        Register (Admin only)
      </button>
    </div>

    <!-- Formularz -->
    <form @submit.prevent="handleSubmit">
      <div class="mb-3">
        <label for="email" class="form-label">Email:</label>
        <input v-model="formData.email" type="email" id="email" class="form-control" required />
      </div>
      <div class="mb-3">
        <label for="password" class="form-label">Password:</label>
        <input v-model="formData.password" type="password" id="password" class="form-control" required />
      </div>

      <div v-if="activeTab === 'register'" class="mb-3">
        <label for="role" class="form-label">Role:</label>
        <select v-model="formData.role" id="role" class="form-select">
          <option value="ENDUSER">End User</option>
          <option value="ADMIN">Admin</option>
        </select>
      </div>

      <button v-if="activeTab === 'login'" type="submit" class="btn btn-primary">Login</button>
      <button v-if="activeTab === 'register'" type="submit" class="btn btn-success">Register</button>
    </form>

    <!-- Komunikat o błędzie -->
    <p v-if="error" class="text-danger mt-2">{{ error }}</p>
  </div>
</template>

<script>
import axios from '@/axios.js';

export default {
  data() {
    return {
      activeTab: 'login',
      formData: {
        email: '',
        password: '',
        role: 'ENDUSER'
      },
      error: ''
    };
  },
  methods: {
    async handleSubmit() {
      this.error = '';
      if (this.activeTab === 'login') {
        await this.handleLogin();
      } else {
        await this.handleRegister();
      }
    },
    async handleLogin() {
      try {
        console.log('Sending login request with:', this.formData);
        const response = await axios.post('/auth/login', {
          email: this.formData.email,
          password: this.formData.password
        });
        console.log('Login response:', response.data);
        const { token } = response.data;
        localStorage.setItem('jwtToken', token);
        this.$router.push('/home');
      } catch (err) {
        console.error('Login error:', err);
        this.error = 'Login failed. Please check your credentials.';
        if (err.response) {
          this.error += ` (Status: ${err.response.status}, Message: ${err.response.data.message || err.response.statusText})`;
        }
      }
    },
    async handleRegister() {
      try {
        const endpoint = this.formData.role === 'ADMIN' ? '/auth/register-admin' : '/auth/register-enduser';
        const response = await axios.post(endpoint, {
          email: this.formData.email,
          password: this.formData.password,
          role: this.formData.role
        });
        const { token } = response.data;
        localStorage.setItem('jwtToken', token);
        this.$router.push('/home');
      } catch (err) {
        this.error = 'Registration failed. Admin role required for registration.';
      }
    }
  }
};
</script>

<style scoped>
.auth-container {
  max-width: 500px;
  margin: 50px auto;
  padding: 20px;
}
</style>