import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    nombre:'',
    problemsSelected : [],
    idsProblems : [],
    newProblem : {},
    myProblems : [],
    clientURL: "localhost:9898",
    isLogged:false,
    user: {
      username: null,
      token: null,
      credits: 0
    },
    submit: {
      show: {
        problem: true,
        solution: false
      },
      form: {
        problem: {
          title: '',
          type: '',
          body: '',
          topics_id: [],
          image: null
        },
        solution: {
          description: '',
          image: null

        }
      },
      types: [
        { id: 1, name: "Multiple Choice"},
        { id: 2, name: "True or False"},
        { id: 3, name: "Short Answer"},
        { id: 4, name: "Long Answer"}
      ],
      editor: [
        [{ header: [2, 3, 4, false] }],
        ["bold", "italic", "underline"],
        [{ list: "ordered" }, { list: "bullet" }],
        ["formula"],
        ["code-block"]
    ]
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
      if (!(this.state.newProblem in this.state.myProblems))
        this.state.myProblems.push(this.state.newProblem)
    },
    updateIds : function (state, newId) {
      this.state.idsProblems.push (newId.valor)
    },
    viewProblems () {
      for (let x of this.state.myProblems){
        console.log (x)
      }
    },
    stateLogged(){
      return this.isLogged;
    },
    logout(){
      this.isLogged = false;
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
