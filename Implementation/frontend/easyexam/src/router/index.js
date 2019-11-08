import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter);

const routes = [
  {
    path: '/',
    name: 'home',
    component: () => import('../views/Home.vue')
  },
  {
    path: '/login',
    name: 'login',
    component: () => import('../views/Login.vue')
  },
  {
    path: '/register',
    name: 'register',
    component: () => import('../views/Register.vue')
  },
  {
    path: '/forgot-password',
    name: 'forgot-password',
    component: () => import('../views/ForgotPassword.vue')
  },
  {
<<<<<<< HEAD
    path: '/reset-password',
    name: 'reset-password',
    component: () => import('../views/ResetPassword.vue')
  },
  {
    path: '/dashboard',
    name: 'dashboard',
    component: () => import('../views/Dashboard.vue')
=======
    path: '/submit',
    name: 'submit',
    component: () => import('../views/Submit.vue')
  },
  {
    path: '/generate',
    name: 'generate',
    component: () => import('../views/Generate.vue')
  },
  {
    path: '/newpass',
    name: 'newpass',
    component: () => import('../views/NewPass.vue')
>>>>>>> 52a022c0669d63270a23ce0f8f8f2fac3204bdea
  },
];

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
});

export default router
