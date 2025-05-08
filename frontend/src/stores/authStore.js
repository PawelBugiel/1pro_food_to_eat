import { defineStore } from 'pinia';
import jwtDecode from 'jwt-decode'; // Biblioteka do dekodowania JWT

export const useAuthStore = defineStore('auth', {
    state: () => ({
        token: localStorage.getItem('jwtToken') || null,
        role: null
    }),
    actions: {
        setToken(token) {
            this.token = token;
            localStorage.setItem('jwtToken', token);
            // Dekodowanie roli z tokenu JWT
            try {
                const decoded = jwtDecode(token);
                this.role = decoded.role; // Zakładamy, że token zawiera pole 'role'
            } catch (error) {
                console.error('Error decoding JWT:', error);
                this.role = null;
            }
        },
        clearAuth() {
            this.token = null;
            this.role = null;
            localStorage.removeItem('jwtToken');
        }
    }
});