import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import axios from 'axios'

import BootstrapVue from 'bootstrap-vue'
Vue.use(BootstrapVue);
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import '@fortawesome/fontawesome-free/css/all.css'
import '@fortawesome/fontawesome-free/js/all.js'
import VueLaTeX2JS from 'latex2vue';
Vue.use(VueLaTeX2JS);
Vue.prototype.$http = axios;
Vue.config.productionTip = false;

/* No Borrar
router.beforeEach((to, from, next) => {
  if (to.fullPath == '/' || to.fullPath == '/register' || to.fullPath == '/login' ||
      to.name == 'reset-password' || to.fullPath == '/forgot-password') {
    next();
  } else if (!store.state.user.token) {
    next('/login');
  } else {
    next();
  }
});
*/

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
