import { createRouter, createWebHistory } from 'vue-router';
import AuthView from '../views/AuthView.vue';
import HomePage from '../views/HomePage.vue';
import FoodItemDetails from '../views/FoodItemDetails.vue';
import UserManagement from '../views/UserManagement.vue'; // Nowy import

const routes = [
  { path: '/', name: 'Auth', component: AuthView },
  { path: '/home', name: 'HomePage', component: HomePage },
  { path: '/food-item/:id', name: 'FoodItemDetails', component: FoodItemDetails },
  { path: '/users', name: 'UserManagement', component: UserManagement } // Nowa trasa
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;