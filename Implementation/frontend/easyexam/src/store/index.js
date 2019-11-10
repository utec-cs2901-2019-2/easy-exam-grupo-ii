import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
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
          topics_id: [
          ],
          image: null
        },
        solution: {
          description: '',
          alternatives: [
            {
              id: 1,
              body: "Lorem Ipsum",
              value: true
            }

          ],
          image: null,
          alt_body: '',
          alt_value: null,
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
        ["code-block"],
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
    createAlternative (state){
      state.submit.form.solution.alternatives.push({
        id: + new Date(),
        body: state.submit.form.solution.alt_body,
        value: state.submit.form.solution.alt_value
      });
      state.submit.form.solution.alt_body = '';
      state.submit.form.solution.alt_value = '';
    }
  },
  actions: {
  },
  modules: {
  }
})
