<template>
  <div class="container mt-4">
    <h2>Admin Dashboard</h2>
    <div class="mb-4">
      <router-link to="/home" class="btn btn-primary me-2">Go to Products</router-link>
      <router-link to="/users" class="btn btn-success">Manage Users</router-link>
    </div>
    <button @click="logout" class="btn btn-danger">Logout</button>
  </div>
</template>

<script>
import { useAuthStore } from '@/stores/authStore';

export default {
  name: 'AdminDashboardView',
  setup() {
    const authStore = useAuthStore();
    return { authStore };
  },
  methods: {
    logout() {
      this.authStore.clearAuth();
      this.$router.push('/');
    }
  }
};
</script>

<style scoped>
.container {
  max-width: 600px;
  margin: 50px auto;
  padding: 20px;
}
</style>