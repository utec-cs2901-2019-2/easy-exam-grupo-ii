<template>
    <div class="mt-5 mx-5">
        <div class="mx-5">
            <b-row>
                <b-col sm=4>
                    <div class="w-100 p-3">
                        <b-img class="w-100 mb-5" src="https://picsum.photos/600/300/?image=25"></b-img>
                        <h4><b>{{user.information.first_name}}</b></h4>
                        <h4><b>{{user.information.last_name}}</b></h4>
                        <h4>{{user.information.email}}</h4>
                        <br>
                        <h5>
                            <b>Problems Submited</b>
                        </h5>
                        <h5>
                            {{user.stats.submitedProb}}
                        </h5>
                        <h5>
                            <b>Problems Obtained</b>
                        </h5>
                        <h5>
                            {{user.stats.selectedProb}}
                        </h5>
                        <h5>
                            <b>Comments</b>
                        </h5>
                        <h5>
                            {{user.stats.numberOfComments}}
                        </h5>
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
                        <b-button class="w-100" variant="dark">
                            Edit Proffile
                        </b-button>
                    </div>
                </b-col>
                <b-col sm=8>
                    <div class="w-100 h-100">
                        <b-tabs justified>
                            <!--b-tab class="p-4" title="Overview" active>

                            </b-tab-->
                            <b-tab class="p-4 h-100" title="Problems Submited">
                                <b-row align-h="between" style="margin:auto">
                                    <b-form-input style="width:90%" type="text" v-model="keyToSearch"></b-form-input>
                                    <b-button style="width:7%" variant="info">Filtrar</b-button>
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
                                    <b-button v-if="creditos > 0" style="margin-top : 10px" href="#" pill variant="light" @click="showModalProblem (problem.idx)">Go problem</b-button>
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
            <!--b-card-group class="float-center">
                <b-card align="center" title="My Profile">
                    <b-row>
                        <b-col md="3">
                            <h2>{{user.information.first_name}} <br> {{user.information.last_name}}</h2>
                        </b-col>
                        <b-col md="9">
                            <b-row>
                                <b-col>
                                    <b-row>Problem submited</b-row>
                                    <b-row>{{user.stats.submitedProb}}</b-row>
                                </b-col>
                                <b-col>
                                    <b-row class="text-center">
                                        <b-card-text> Problems selected </b-card-text>
                                    </b-row>
                                    <b-row class="text center">
                                        <b-card-text> {{user.stats.selectedProb}} </b-card-text>
                                    </b-row>
                                </b-col>
                                <b-col>
                                    <b-row>Comments</b-row>
                                    <b-row>{{user.stats.numberOfComments}}</b-row>
                                </b-col>
                            </b-row>
                        </b-col>
                    </b-row>
                </b-card>
            </b-card-group> 
        <div class="mt-3">
            <b-card-group deck class="mb-3">
                <b-card border-variant="light" header="Information" class="text-center">
                    <b-row>
                        <b-col>
                        <b-card-text>First name:</b-card-text>
                        </b-col>
                        <b-col>
                        <b-card-text>{{user.information.first_name}}</b-card-text>
                        </b-col>
                    </b-row>
                    <b-row>
                        <b-col>
                        <b-card-text>Last name:</b-card-text>
                        </b-col>
                        <b-col>
                        <b-card-text>{{user.information.last_name}}</b-card-text>
                        </b-col>
                    </b-row>
                    <b-row>
                        <b-col>
                        <b-card-text>Email:</b-card-text>
                        </b-col>
                        <b-col>
                        <b-card-text>{{user.information.email}}</b-card-text>
                        </b-col>
                    </b-row>
                    <b-row>
                        <b-col>
                        <b-card-text>Institution: </b-card-text>
                        </b-col>
                        <b-col>
                        <b-card-text>{{user.information.institution}}</b-card-text>
                        </b-col>
                    </b-row>
                    <b-row>
                        <b-col>
                        <b-card-text>Country: </b-card-text>
                        </b-col>
                        <b-col>
                        <b-card-text>{{user.information.country}}</b-card-text>
                        </b-col>
                    </b-row>
                    <b-row>
                        <b-col>
                        <b-card-text>Date of Birth: </b-card-text>
                        </b-col>
                        <b-col>
                        <b-card-text>{{user.information.DateOfBirth}}</b-card-text>
                        </b-col>
                    </b-row>
                    <b-row>
                        <b-button variant="primary" class="float-right"> Edit </b-button>
                    </b-row>
                </b-card>
                <b-card border-variant="light" header="Exams" align="center">
                    <b-list-group>
                        <b-list-group-item href="#" active class="flex-column align-items-start">
                            <div class="d-flex w-100 justify-content-between">
                                <h5 class="mb-1">List group item heading</h5>
                                <small>3 days ago</small>
                            </div>
                            <p class="mb-1">
                            Donec id elit non mi porta gravida at eget metus. Maecenas sed diam eget risus varius blandit.
                            </p>
                            <small>Donec id elit non mi porta.</small>
                        </b-list-group-item>
                        <b-list-group-item href="#" class="flex-column align-items-start">
                            <div class="d-flex w-100 justify-content-between">
                                <h5 class="mb-1">List group item heading</h5>
                                <small class="text-muted">3 days ago</small>
                            </div>

                            <p class="mb-1">
                                Donec id elit non mi porta gravida at eget metus. Maecenas sed diam eget risus varius blandit.
                            </p>

                            <small class="text-muted">Donec id elit non mi porta.</small>
                        </b-list-group-item>

                        <b-list-group-item href="#" disabled class="flex-column align-items-start">
                            <div class="d-flex w-100 justify-content-between">
                                <h5 class="mb-1">Disabled List group item</h5>
                                <small class="text-muted">3 days ago</small>
                            </div>

                            <p class="mb-1">
                                Donec id elit non mi porta gravida at eget metus. Maecenas sed diam eget risus varius blandit.
                            </p>

                            <small class="text-muted">Donec id elit non mi porta.</small>
                        </b-list-group-item>
                    </b-list-group>
                </b-card>
            </b-card-group>
        </div-->
    </div>
</template>

<script>
import axios from 'axios'

export default {

    data() {
        return {
            infoproblems : [],
            keyToSearch : '',
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
        axios.get("http://" + this.$store.state.clientURL +  "/problem/v1/problem/getProblemTopics")
        .then(response => (this.infoproblems = response.data))       
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