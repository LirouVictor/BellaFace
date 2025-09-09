import { createRouter, createWebHistory } from 'vue-router';
import { useAuthStore } from '../stores/useAuthStore'; 
import LoginView from '../views/LoginView.vue';
import LojaView from '../views/LojaView.vue';

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/login',
      name: 'login',
      component: LoginView
    },
    {
      path: '/',
      name: 'loja',
      component: LojaView,
      meta: { requiresAuth: true } // Proteger rota
    }
  ]
});

// "Guarda de rota"
router.beforeEach((to, from, next) => {
  const authStore = useAuthStore();
  const isAuthenticated = authStore.estaLogado;
  const requiresAuth = to.meta.requiresAuth;

  if (requiresAuth && !isAuthenticated) {
    next({ name: 'login' });
    return;
  }

  if (to.name === 'login' && isAuthenticated) {
    next({ name: 'loja' }); 
    return;
  }
  
  next();
});

export default router;