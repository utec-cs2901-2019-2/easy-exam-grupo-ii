import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: () => import('../views/Home.vue')
  },
  {
    path: '/login',
    name: 'login',
    component: () => import('../views/AccountMag/Login.vue')
  },
  {
    path: '/register',
    name: 'register',
    component: () => import('../views/AccountMag/Register.vue')
  },
  {
    path: '/recover',
    name: 'recover',
    component: () => import('../views/AccountMag/Recover.vue')
  },
  {
    path: '/submit',
    name: 'submit',
    component: () => import('../views/Submit.vue')
  },
];

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
