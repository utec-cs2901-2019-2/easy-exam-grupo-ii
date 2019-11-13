import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
<<<<<<< HEAD
    isLogged:false,
=======
    user: {
      username: '',
      token: '',
      credits: 0
    },
>>>>>>> 98349889e8976c8e9f4e0908f4181db1f5a89b1c
    submit: {
      show: {
        problem: true,
        solution: false
      },
      form: {
        problem: {
          title: '',
          body: '',
          topics_id: [
          ],
          image: null
        },
        solution: {
          description: '',
          image: null
        }
      }
    }

  },
  mutations: {
    updateViewNext () {
      this.state.submit.show.problem = false;
      this.state.submit.show.solution = true;
    },
    updateViewBack () {
      this.state.submit.show.problem = true;
      this.state.submit.show.solution = false;
    },
    stateLogged(){
      return this.isLogged;
    }
  },
  actions: {
  },
  modules: {
  },
  getters:{
    getIsLogged(state){
      return state.isLogged;
    }
  }
})
