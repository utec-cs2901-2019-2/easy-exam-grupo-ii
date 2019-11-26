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
    path: '/reset-password',
    name: 'reset-password',
    component: () => import('../views/ResetPassword.vue')
  },
  {
    path: '/dashboard',
    name: 'dashboard',
    component: () => import('../views/Dashboard.vue')
  },
  {
    path: '/generate',
    name: 'generate',
    component: () => import('../views/Generate.vue')
  },
  {
    path: '/submit',
    name: 'submit',
    component: () => import('../views/Submit.vue')
  },
  {
    path: '/profile',
    name: 'profile',
    component: () => import('../views/Profile.vue')
  },
  {
    path: '/reported-problems',
    name: 'reported-problems',
    component: () => import('../views/ReportedProblem.vue')
  },
  {
    path: '/reported-user',
    name: 'reported-user',
    component: () => import('../views/ReportedUser.vue')
  },
  {
    path: '/admin-creator',
    name: 'admin-creator',
    component: () => import('../views/AdminCreator.vue')
  }
];

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
});

export default router
