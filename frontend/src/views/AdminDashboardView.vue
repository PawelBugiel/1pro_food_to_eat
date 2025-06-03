<template>
  <div class="container mt-4">
    <div class="d-flex justify-content-end mb-4">
      <button @click="logout" class="btn btn-info btn-sm">Logout</button>
    </div>

    <h2>Admin Dashboard</h2>
    <div class="d-flex justify-content-center mb-4">
      <router-link to="/home" class="btn btn-custom-add btn-sm">Go to Products</router-link>
      <router-link to="/users" class="btn btn-custom-register btn-sm ms-3">Manage Users</router-link>
    </div>
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

