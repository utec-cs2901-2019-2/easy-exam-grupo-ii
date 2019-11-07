<template>

    <div class="mt-5">

        <b-modal ref="modal-problem" size = "xl" >

            <template v-slot:modal-title>
                {{modal_titleProblem}}
            </template>

            <b-row>
                <b-col cols = "9">
                    <b-button variant = "info" pill style="margin:3px" v-for="(tag, index) of modal_tagsProblem" v-bind:key = "index">
                    {{tag}}
                    </b-button>
                </b-col>
                <b-col cols = "3">
                    <b-button disabled style="width:100%">
                        {{types[modal_selectProblem ['type']]}}
                    </b-button>
                </b-col>
            </b-row>
            <br>
            <b-card no-body class="text-center" style="height:250px; margin-top:10px; position : relative; overflow-y : scroll">
                <p>{{modal_desProblem}}</p>
            </b-card>

            <b-card style="width : 95%; height: 300px">
                <b-row>
                    <b-col cols = "10" vertical-align>
                        <b-row>
                            <b-button>
                                Comments
                            </b-button>
                        </b-row>
                        
                        <ul class="list-unstyled">
                            <b-media tag="li" style="margin : 10px">
                                <b-button disabled>
                                    comment 1
                                </b-button>
                            </b-media>
                            <b-media tag="li" style="margin : 10px">
                                <b-button disabled>
                                    comment 1
                                </b-button>
                            </b-media>
                            <b-media tag="li" style="margin : 10px">
                                <b-button disabled>
                                    comment 1
                                </b-button>
                            </b-media>
                        </ul>
                    </b-col>
                    <b-col cols = "2">
                        <b-button disabled>
                            Puntuation
                        </b-button>
                        <p>18 users</p>
                    </b-col>
                </b-row>
            </b-card>

            <template v-slot:modal-footer>
                <b-row style="width : 100%">
                    <b-col cols = "3">
                    <b-button variant="outline-info" @click="show=false">Get Solution</b-button>
                    </b-col>
                    <b-col cols = "3">
                    <b-button variant="outline-info" @click="show=false">Comments</b-button>
                    </b-col>
                    <b-col cols = "3">
                    <b-button variant="outline-danger" @click="show=false">Report</b-button>
                    </b-col>
                    <b-col cols = "3">
                    <b-button variant="outline-danger" @click="cancel()">Cancel</b-button>
                    </b-col>
                </b-row>
            </template>


        </b-modal>

        <b-row class="justify-content-center" style="margin:0">
            <b-col cols = "3">
                <div role="tablist">
                    <b-card no-body >
                        <b-card-header header-tag="header" class="p-1" role="tab">
                            <b-button block href="#" v-b-toggle.accordion-1 variant="info">Subjects</b-button>
                        </b-card-header>
                        <b-collapse id="accordion-1" visible accordion="my-accordion" role="tabpanel">
                            <b-card-body>
                                <b-list-group>
                                    <b-list-group-item button v-for="(sub, index) of subjects" v-bind:key="index">
                                        <b-button @click="selectSubject(sub)">
                                        {{sub}}
                                        </b-button>
                                    </b-list-group-item>
                                </b-list-group>
                            </b-card-body>
                        </b-collapse>
                    </b-card>

                    <b-card no-body >
                        <b-card-header header-tag="header" class="p-1" role="tab">
                            <b-button block href="#" v-b-toggle.accordion-2 variant="info">Type of problem</b-button>
                        </b-card-header>
                        <b-collapse id="accordion-2" accordion="my-accordion" role="tabpanel">
                            <b-card-body>
                            <b-list-group>
                                <b-list-group-item class="d-flex justify-content-between align-items-center" href="#">Default list group item<b-badge variant="primary" pill>14</b-badge></b-list-group-item>
                                <b-list-group-item href="#" variant="primary">Primary list group item</b-list-group-item>
                                <b-list-group-item href="#" variant="secondary">Secondary list group item</b-list-group-item>
                                <b-list-group-item href="#" variant="success">Success list group item</b-list-group-item>
                                <b-list-group-item href="#" variant="danger">Danger list group item</b-list-group-item>
                                <b-list-group-item href="#" variant="warning">Warning list group item</b-list-group-item>
                                <b-list-group-item href="#" variant="info">Info list group item</b-list-group-item>
                                <b-list-group-item href="#" variant="light">Light list group item</b-list-group-item>
                                <b-list-group-item href="#" variant="dark">Dark list group item</b-list-group-item>
                            </b-list-group>
                            </b-card-body>
                        </b-collapse>
                    </b-card>
                </div>
            </b-col>

            <b-col cols = "9">

                <b-card style="width:80%" header-tag="header">
                    <template v-slot:header>
                        <b-row align-h="between" style="margin:auto">
                            <b-form-input style="width:92%" type="text" v-model="keyToSearch"></b-form-input>
                            <b-button variant="info">Filtrar</b-button>
                        </b-row>
                    </template>

                    <b-card-body style="position:relative; height:700px; overflow-y:scroll;">
                        <div role="tablist">
                            <b-card style = "margin:20px; background: #d4d4d4" class="mb-1" v-for="(problem, index) of filtrar" v-bind:key = "index">
                                <b-card-title><b>{{problem.title}}</b></b-card-title>
                                <b-row>
                                    <b-col cols = "10">
                                        <b-card-text style="height : 50px; position:relative; overflow-y:hidden">
                                    {{problem.body}}
                                </b-card-text>
                                <div style="margin-top : 10px">
                                    <b-button variant="info" style = "margin:5px" v-for="(tag, index) of problem.tags" v-bind:key="index">{{tag}}</b-button>
                                </div>
                                    </b-col>
                                    <b-col cols = "2">
                                        <ul class="list-unstyled">
                                        <li>
                                        <b-button disabled style="width : 50%; height: 50%; font-size : x-large">
                                            {{problem.score}}
                                        </b-button>
                                        </li>
                                        <!-- TODO: retrieve table "problem_topic" and
                                        relate it to each problem -->
                                        <br>
                                        <li>
                                        <b-button disabled variant = "light" style="font-size : small; width : 50%"><b>
                                            {{problem.type}}</b>
                                        </b-button>
                                        </li>
                                        </ul>
                                    </b-col>
                                </b-row>
                                
                                <b-button style="margin-top : 10px" href="#" pill variant="light" @click="showModalProblem (problem.id)">Go problem</b-button>
                            </b-card>
                        </div>
                    </b-card-body>
                </b-card>

            </b-col>
        </b-row>

    </div>

</template>



<script>
import json from
'../../../../backend/backend/src/main/java/com/easyexam/JsonConnector/problems.json'
export default {
    data :  () => ({

            filtrarAvailable : true,

            subjectToSearch : '',

            keyToSearch : '',

            results : [],

            mensajevacio : '',

            totalproblems : json,

            infoproblems : json,

            modal_titleProblem : '',

            modal_desProblem : '',

            modal_tagsProblem : [],

            modal_selectProblem : {},

            subjects : ['Divide and conquer', 'Algorithms', 'Maths'],

            types : {'SA' : 'Short Answer', 'LA' : 'Long Answer', 'MC' : 'Multiple Choice', 'TF': 'True or False'}
        
    }),

    methods: {
        imprimir : function() {
            for (let problem of this.infoproblems){
                let stringToSearch = problem.tags.toString().concat (" ",
                problem.description, " ", problem.title).toLowerCase()
                console.log (stringToSearch)
            }
        },

        showModalProblem(index) {
            this.modal_titleProblem = this.infoproblems [index].title
            this.modal_desProblem = this.infoproblems [index].body
            this.modal_tagsProblem = this.infoproblems [index].tags
            this.modal_selectProblem = this.infoproblems [index]
            
            this.$refs['modal-problem'].show()
            console.log(index)
      },

        selectSubject (Subject) {
            this.filtrarAvailable = false
            this.subjectToSearch = Subject
            /*let s = Subject.toLowerCase()
            let tempproblems = this.totalproblems
            for( var i = 0; i < this.totalproblems.length; i++){ 
                if (!this.totalproblems[i]['tags'].includes(s)) {
                    tempproblems.splice(i, 1)
                }

            }
            this.infoproblems = tempproblems*/

        }

    },

    computed : {
        filtrar : function () {
            let res = []

            if (this.filtrarAvailable)
            {
                if (this.keyToSearch === '') {
                    let id = 0
                    for (let problem of this.infoproblems) {
                        problem["id"] = id
                        id = id + 1
                        res.push (problem)
                    }
                }
                else {
                        let id = 0
                        for (let problem of this.infoproblems) {
                            problem["id"] = id
                            id = id + 1
                            let stringToSearch = problem.tags.toString().concat
                            (" ", problem.body, " ", problem.title).toLowerCase()
                            if (stringToSearch.includes (this.keyToSearch.toLowerCase())) {
                                res.push (problem)
                            }
                        }
                }

                return res
            }
            else
            {
                let id = 0
                for (let problem of this.infoproblems) {
                    problem["id"] = id
                    id = id + 1
                    let stringToSearch = problem.tags.toString().toLowerCase()
                    if (stringToSearch.includes (this.subjectToSearch.toLowerCase())) {
                        res.push (problem)
                    }
                }
                return res
            }
        }
    }


}
</script>
