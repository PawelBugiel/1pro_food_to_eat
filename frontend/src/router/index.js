import { createRouter, createWebHistory } from 'vue-router'; // Import funkcji z Vue Routera
import HomePage from '../views/HomePage.vue'; // Importuj komponenty
import FoodItemDetails from '../views/FoodItemDetails.vue';

const routes = [
  { path: '/', name: 'HomePage', component: HomePage },
  { path: '/food-item/:id', name: 'FoodItemDetails', component: FoodItemDetails }
];

// Tworzenie instancji routera z odpowiednią konfiguracją (w Vue 3 używamy createRouter)
const router = createRouter({
  history: createWebHistory(), // Używamy createWebHistory do historii przeglądarki
  routes, // Przekazujemy zdefiniowane trasy
});

export default router;
