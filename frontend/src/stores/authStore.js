import { defineStore } from 'pinia';
import { jwtDecode } from 'jwt-decode'; // Zmieniamy import na nazwany eksport

export const useAuthStore = defineStore('auth', {
    state: () => ({
        token: localStorage.getItem('jwtToken') || null,
        role: null
    }),
    actions: {
        setToken(token) {
            this.token = token;
            localStorage.setItem('jwtToken', token);
            try {
                const decoded = jwtDecode(token); // Używamy jwtDecode jako funkcji
                console.log('Decoded JWT in authStore:', decoded);
                this.role = decoded.role ? decoded.role.toUpperCase() : null;
                console.log('Set role in authStore:', this.role);
                if (!this.role) {
                    console.error('Role not found in token! Defaulting to ENDUSER');
                    this.role = 'ENDUSER';
                }
            } catch (error) {
                console.error('Error decoding JWT:', error);
                this.role = 'ENDUSER';
            }
        },
        clearAuth() {
            this.token = null;
            this.role = null;
            localStorage.removeItem('jwtToken');
        }
    }
});