import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    nombre:'',
    problemsSelected : [],
    newProblem : {},
    myProblems : [],
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
    updateNewProblem :function (state, nProblem) {
      this.state.newProblem = nProblem.valor
    },
    updateMyProblems () {
      this.state.myProblems.push(this.state.newProblem)
    },
    viewProblems () {
      for (let x of this.state.myProblems){
        console.log (x)
      }
    }
  },
  actions: {
  },
  modules: {
  }
})
