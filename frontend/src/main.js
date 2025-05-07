import {createApp} from 'vue'; // Importuj createApp
import App from './App.vue';
import router from './router'; // Importuj router
import 'bootstrap/dist/css/bootstrap.min.css';


// Tworzenie instancji aplikacji
createApp(App)
    .use(router) // Dodaj router do aplikacji
    .mount('#app'); // Montuj aplikację do elementu DOM o id "app"
