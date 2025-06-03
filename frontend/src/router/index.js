import { createRouter, createWebHistory } from 'vue-router';
import AuthView from '../views/AuthView.vue';
import ProductListView from '../views/ProductListView.vue';
import FoodItemDetails from '../views/FoodItemDetails.vue';
import UserManagementView from '../views/UserManagementView.vue';
import AdminDashboardView from '../views/AdminDashboardView.vue';

const routes = [
  { path: '/', name: 'Auth', component: AuthView },
  { path: '/home', name: 'HomePage', component: ProductListView },
  { path: '/food-item/:id', name: 'FoodItemDetails', component: FoodItemDetails },
  { path: '/users', name: 'UserManagement', component: UserManagementView },
  { path: '/admin', name: 'AdminDashboardView', component: AdminDashboardView }
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});
 
export default router;