<template>
    <div class="mt-5 mx-5">
        <div class="mx-5">
            <b-row>
                <b-col sm=4>
                    <div class="w-100 p-3">
                        <!--b-img class="w-100 mb-5" src="https://picsum.photos/600/300/?image=25"></b-img-->
                        <h4><b>{{user.information.first_name}}</b></h4>
                        <h4><b>{{user.information.last_name}}</b></h4>
                        <h4>{{user.information.email}}</h4>
                        <br>
                        <h5>
                            <b>Problems Submited</b>
                        </h5>
                        <h5>
                            {{infoproblems.length}}
                        </h5>
                        <h5>
                            <b>Problems Obtained</b>
                        </h5>
                        <h5>
                            {{obproblems.length}}
                        </h5>
                        <!--h5>
                            <b>Comments</b>
                        </h5>
                        <h5>
                            {{user.stats.numberOfComments}}
                        </h5-->
                        <h5>
                            <b>Institution</b>
                        </h5>
                        <h5>
                            {{user.information.institution}}
                        </h5>
                        <h5>
                            <b>Country</b>
                        </h5>
                        <h5>
                            {{user.information.country}}
                        </h5>
                        <h5>
                            <b>Date of Birth</b>
                        </h5>
                        <h5>
                            {{user.information.DateOfBirth}}
                        </h5>
                        <!--b-button class="w-100" variant="dark">
                            Edit Proffile
                        </b-button-->
                    </div>
                </b-col>
                <b-col sm=8>
                    <div class="w-100 h-100">
                        <b-tabs justified>
                            <!--b-tab class="p-4" title="Overview" active>

                            </b-tab-->
                            <b-tab class="p-4 h-100" title="Problems Submited">
                                <b-row align-h="between" style="margin:auto">
                                    <b-form-input style="width:80%" type="text" v-model="keyToSearch"></b-form-input>
                                    <b-form-select style="width:10%"
                                    id="input-3"
                                    v-model="form_select.tsort"
                                    :options="form_select.sorts"
                                    required
                                    ></b-form-select>
                                    <b-button style="width:10%" variant="info">Filtrar</b-button>
                                </b-row>

                                <b-card style = "margin:20px; background: #d4d4d4" class="mb-1" v-for="(problem, index) of filtrar" v-bind:key = "index">
                                    <b-card-title><b>{{problem.title}}</b></b-card-title>
                                    <b-row style="height :70px">
                                        <b-col cols = "10" style="height:100%">
                                            <div style="margin-top : 10px">
                                                <b-button variant="info" style = "margin:5px" v-for="(tag, index) of problem.topicsString" v-bind:key="index">{{tag}}</b-button>
                                            </div>
                                        </b-col>
                                        <b-col cols = "2" style="height:100%">
                                            <ul class="list-unstyled">
                                                <li>
                                                    <b-button disabled style="width : 50%">
                                                        {{problem.score}}
                                                    </b-button>
                                                </li>
                                                <li>
                                                    <b-button disabled variant = "light" style="font-size : small; width : 50%"><b>
                                                        {{problem.type}}</b>
                                                    </b-button>
                                                </li>
                                            </ul>
                                        </b-col>
                                    </b-row>
                                    <b-button style="margin-top : 10px" href="#" pill variant="light" @click="showModalProblem (problem.idx)">Go problem</b-button>
                                </b-card>
                            </b-tab>
                            <b-tab class="p-4 h-100" title="Problems Obtained">
                                <b-row align-h="between" style="margin:auto">
                                    <b-form-input style="width:80%" type="text" v-model="keyToSearch2"></b-form-input>
                                    <b-form-select style="width:10%"
                                    id="input-3"
                                    v-model="form_select.tsort"
                                    :options="form_select.sorts"
                                    required
                                    ></b-form-select>
                                    <b-button style="width:10%" variant="info">Filtrar</b-button>
                                </b-row>

                                <b-card style = "margin:20px; background: #d4d4d4" class="mb-1" v-for="(problem, index) of filtrar2" v-bind:key = "index">
                                    <b-card-title><b>{{problem.title}}</b></b-card-title>
                                    <b-row style="height :70px">
                                        <b-col cols = "10" style="height:100%">
                                            <div style="margin-top : 10px">
                                                <b-button variant="info" style = "margin:5px" v-for="(tag, index) of problem.topicsString" v-bind:key="index">{{tag}}</b-button>
                                            </div>
                                        </b-col>
                                        <b-col cols = "2" style="height:100%">
                                            <ul class="list-unstyled">
                                                <li>
                                                    <b-button disabled style="width : 50%">
                                                        {{problem.score}}
                                                    </b-button>
                                                </li>
                                                <li>
                                                    <b-button disabled variant = "light" style="font-size : small; width : 50%"><b>
                                                        {{problem.type}}</b>
                                                    </b-button>
                                                </li>
                                            </ul>
                                        </b-col>
                                    </b-row>
                                    <b-button style="margin-top : 10px" href="#" pill variant="light" @click="showModalProblem (problem.idx)">Go problem</b-button>
                                </b-card>
                            </b-tab>
                            <b-tab class="p-4" title="Exams">

                                <b-container>
                                    <center>
                                    <b-row style="width:80%" align-self="center">
                                        <b-col sm=5>
                                            <b-button variant="outline-dark" class="w-100 mx-3">
                                                <h4><b>Title Exam</b></h4>
                                                <h5># problems</h5>
                                                <h5>
                                                    date
                                                </h5>
                                            </b-button>
                                        </b-col>
                                        <b-col sm=2></b-col>
                                        <b-col sm=5>
                                            <b-button variant="outline-dark" class="w-100 mx-3">
                                                <h4><b>Title Exam</b></h4>
                                                <h5># problems</h5>
                                                <h5>
                                                    date
                                                </h5>
                                            </b-button>
                                        </b-col>
                                    </b-row>
                                    <br>
                                    <b-row style="width:80%" align-self="center">
                                        <b-col sm=5>
                                            <b-button variant="outline-dark" class="w-100 mx-3">
                                                <h4><b>Title Exam</b></h4>
                                                <h5># problems</h5>
                                                <h5>
                                                    date
                                                </h5>
                                            </b-button>
                                        </b-col>
                                        <b-col sm=2></b-col>
                                        <b-col sm=5>
                                            <b-button variant="outline-dark" class="w-100 mx-3">
                                                <h4><b>Title Exam</b></h4>
                                                <h5># problems</h5>
                                                <h5>
                                                    date
                                                </h5>
                                            </b-button>
                                        </b-col>
                                    </b-row>
                                    <br>

                                    <b-row style="width:80%" align-self="center">
                                        <b-col sm=5>
                                            <b-button variant="outline-dark" class="w-100 mx-3">
                                                <h4><b>Title Exam</b></h4>
                                                <h5># problems</h5>
                                                <h5>
                                                    date
                                                </h5>
                                            </b-button>
                                        </b-col>
                                        <b-col sm=2></b-col>
                                        <b-col sm=5>
                                            <b-button variant="outline-dark" class="w-100 mx-3">
                                                <h4><b>Title Exam</b></h4>
                                                <h5># problems</h5>
                                                <h5>
                                                    date
                                                </h5>
                                            </b-button>
                                        </b-col>
                                    </b-row>
                                    </center>
                                </b-container>

                            </b-tab>
                        </b-tabs>
                    </div>
                </b-col>
            </b-row>
        </div>
    </div>
</template>

<script>
import axios from 'axios'

export default {

    data() {
        return {

            form_select : {
                tsort : null,
                sorts : [{ text: 'Order by', value: null }, 'Title', 'Score', 'qualifiers'],
            },
            infoproblems : [],
            obproblems : [],
            keyToSearch : '',
            keyToSearch2 : '',
            show : true,
            form: {
                email: '',
                name: '',
                food: null,
                checked: []
                },
            user: {
                stats:{
                    submitedProb: 12,
                    selectedProb: 10,
                    numberOfComments: 1
                },
                information:{
                    first_name: "Anggie",
                    last_name: "Yengle",
                    email: "angie.yengle@gmail.com",
                    institution: "UTEC",
                    country: "Perú",
                    DateOfBirth: "12/01/2019"
                }
            }

        }
    },
    mounted() {
        axios.get("http://" + this.$store.state.clientURL +  "/problem/v1/problem/getProblemsSubmitedByUser?idUser=" + this.$store.state.user.id)
        .then(response => (this.infoproblems = response.data));
        axios.get('http://' + this.$store.state.clientURL + '/problem/v1/problem/getProblemsSelected?id=' + this.$store.state.user.id)
        .then(response => (this.obproblems = response.data))
    },
    computed: {
        filtrar : function () {
            let res = []
            console.log(this.infoproblems)
            if(this.infoproblems.length > 0){
                let id = 0
                for (let problem of this.infoproblems) {
                    problem["idx"] = id
                    id = id + 1
                        if (this.keyToSearch === '')
                            {
                                        res.push (problem)
                            }
                        else
                            {
                                let stringToSearch = problem.topicsString.toString().concat (" ", problem.body, " ", problem.title).toLowerCase ()
                                if (stringToSearch.includes (this.keyToSearch.toLowerCase()))
                                {
                                        res.push (problem)
                                }
                            }
                    
                }
            }

            if (this.form_select.tsort === 'Title'){
                res.sort (function (a,b) {
                    if (a.title < b.title) return -1
                    else return 1
                })
            }
            else if (this.form_select.tsort === 'Score'){
                res.sort (function (a,b) {
                    if (a.score > b.score) return -1
                    else return 1
                })
            }
            else if (this.form_select.tsort === 'qualifiers'){
                res.sort (function (a,b) {
                    if (a.qualifiers > b.qualifiers) return -1
                    else return 1
                })
            }

            return res
        },
        filtrar2 : function () {
            let res = []
            console.log("hola")
            console.log(this.obproblems)
            if(this.obproblems.length > 0){
                let id = 0
                for (let problem of this.obproblems) {
                    problem["idx"] = id
                    id = id + 1
                        if (this.keyToSearch2 === '')
                            {
                                        res.push (problem)
                            }
                        else
                            {
                                let stringToSearch = problem.topicsString.toString().concat (" ", problem.body, " ", problem.title).toLowerCase ()
                                if (stringToSearch.includes (this.keyToSearch2.toLowerCase()))
                                {
                                        res.push (problem)
                                }
                            }
                    
                }
            }

            if (this.form_select.tsort === 'Title'){
                res.sort (function (a,b) {
                    if (a.title < b.title) return -1
                    else return 1
                })
            }
            else if (this.form_select.tsort === 'Score'){
                res.sort (function (a,b) {
                    if (a.score > b.score) return -1
                    else return 1
                })
            }
            else if (this.form_select.tsort === 'qualifiers'){
                res.sort (function (a,b) {
                    if (a.qualifiers > b.qualifiers) return -1
                    else return 1
                })
            }

            return res
        },
    },
    methods: {
        onSubmit(evt) {
        evt.preventDefault()
        alert(JSON.stringify(this.form))
      },
      onReset(evt) {
        evt.preventDefault()
        // Reset our form values
        this.form.email = ''
        this.form.name = ''
        this.form.food = null
        this.form.checked = []
        // Trick to reset/clear native browser form validation state
        this.show = false
        this.$nextTick(() => {
          this.show = true
        })
      }
    },
}
</script>