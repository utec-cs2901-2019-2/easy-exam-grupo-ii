<template>
    <div class="mt-5 mx-5">
        <div class="mx-5">
            <b-row>
                <b-col cols="6" md="4">
                    <b-card class="w-100 p-3">
                        <!--b-img class="w-100 mb-5" src="https://picsum.photos/600/300/?image=25"></b-img-->
                        <h4><b>{{user.information.firstname}}</b></h4>
                        <h4><b>{{user.information.lastname}}</b></h4>
                        <h4>{{user.information.user.email}}</h4>
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
                            {{user.information.date_birth.slice(0,10)}}
                        </h5>
                        <!--b-button class="w-100" variant="dark">
                            Edit Proffile
                        </b-button-->
                    </b-card>
                </b-col>
                <b-col cols="12" md="8">
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
                                <b-row align-h="between" style="margin:auto" fixed>
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
    created(){
        this.items = JSON.parse(sessionStorage.getItem('items'))
        this.$store.state.user.username = this.items.map(items =>items.username).toString()
        this.$store.state.user.token = this.items.map(items =>items.token).toString()
        this.$store.state.user.credits = parseInt(this.items.map(items =>items.credits))
        this.$store.state.user.id = parseInt(this.items.map(items => items.id))
        this.$store.state.isLogged = true;
        },


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
            user: {
                stats:{
                    submitedProb: 12,
                    selectedProb: 10,
                    numberOfComments: 1
                },
                information:{
                    date_birth : '11/11/1111',
                    user : {
                        email : ''
                    }
                }
            }

        }
    },
    mounted() {
        axios.get("http://" + this.$store.state.clientURL +  "/problem/v1/problem/getProblemsSubmitedByUser?idUser=" + this.$store.state.user.id)
        .then(response => (this.infoproblems = response.data));
        axios.get('http://' + this.$store.state.clientURL + '/problem/v1/problem/getProblemsSelected?id=' + this.$store.state.user.id)
        .then(response => (this.obproblems = response.data))
        axios.get('http://' + this.$store.state.clientURL + '/teacher/v1/teacher/getDatos?idTeacher=' + this.$store.state.user.id)
        .then(response => (this.user.information = (response.data)))
    },
    computed: {
        filtrar : function () {
            let res = []
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
    },
}
</script>
