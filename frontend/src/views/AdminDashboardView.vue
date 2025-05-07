<template>
  <div class="container mt-4">
    <h2>Admin Dashboard</h2>
    <div class="mb-4">
      <router-link to="/home" class="btn btn-primary me-2">Go to Products</router-link>
      <router-link to="/users" class="btn btn-success">Manage Users</router-link>
    </div>
  </div>
</template>

<script>
export default {
  name: 'AdminDashboard'
};
</script>

<style scoped>
.container {
  max-width: 600px;
  margin: 50px auto;
  padding: 20px;
}
</style>