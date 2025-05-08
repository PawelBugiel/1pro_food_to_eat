import {createApp} from 'vue'; // Importuj createApp
import { createPinia } from 'pinia'; // Import Pinia
import App from './App.vue';
import router from './router'; // Importuj router
import 'bootstrap/dist/css/bootstrap.min.css';

const app = createApp(App);
const pinia = createPinia(); // Inicjalizacja Pinia

app.use(pinia); // Podpięcie Pinia do aplikacji
app.use(router);
app.mount('#app');
