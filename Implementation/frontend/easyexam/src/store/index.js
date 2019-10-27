import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    nombre:''
  },
  mutations: {
    changeNombre(state,n){
      state.nombre=n;
    }
  },
  actions: {
  },
  modules: {
  }
})
