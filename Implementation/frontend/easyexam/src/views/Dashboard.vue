<template>

    <div class="mt-5">

        <!-- START MODAL FOR PROBLEM -->

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
            <b-row align-h="around" style="height: 300px">
                <b-col cols = "1,5"></b-col>
                <b-col cols="9">
                    <b-row>
                        <b-card bg-variant="secondary" text-variant="white" no-body header="Comments">
                        </b-card>
                    </b-row>
                    
                    <ul class="list-unstyled" style="width: 90%; height: 200px; position: relative; overflow-y:scroll">
                        <b-media v-for="(com, key) of commentsInfo" v-bind:key = "key" tag="li" style="margin : 10px; width: 90%" >
                            {{com}}
                            <b-card>
                                <h4>{{com.author}}</h4>
                                <b-card-text>
                                    {{com.description}}
                                </b-card-text>
                            </b-card>
                        </b-media>
                    </ul>
                </b-col>
                <b-col cols="2">
                        <b-row>
                            <b-button variant="info" style="font-size:large">
                                <b style="margin-left: 10px; margin-right: 10px">Score</b>
                            </b-button>
                        </b-row>
                        <br>
                        <b-row>
                            <b-button variant="success" style="width : 60px; height: 60px; font-size : x-large">
                                {{modal_selectProblem ['score']}}
                            </b-button>
                        </b-row>
                        <br>
                </b-col>
            </b-row>
            

            <template v-slot:modal-footer>
                <b-row style="width : 100%">
                    <b-col cols = "4">
                    <center>
                        <b-button variant="outline-info" @click="hideModalProblem">Get Problem</b-button>
                    </center>
                    </b-col>
                    <b-col cols = "4">
                    <center>
                        <b-button variant="outline-info" @click="showComment()">Comment</b-button>
                    </center>
                    </b-col>
                    <b-col cols = "4">
                    <center>
                        <b-button variant="outline-danger" @click="cancel()">Cancel</b-button>
                    </center>
                    </b-col>
                </b-row>
            </template>
        </b-modal>

        <!-- END MODAL FOR PROBLEM-->
        <!-- START MODAL FOR COMMENTS -->

        <b-modal ref="ModalComment" title="New Comment" hide-footer>
            <b-form @submit="onSubmit" @reset="onReset" >
                <b-form-group
                    id="input-group-1"
                    label="Write your new comment:"
                    label-for="input-1"
                >
                    <b-form-textarea
                    id="textarea-state"
                    v-model="newcomment"
                    :state="newcomment.length <= 500"
                    placeholder="Enter a maximun of 500 characters"
                    rows="5"
                    ></b-form-textarea>

                </b-form-group>
                    <b-button type="submit" variant="primary">Submit</b-button>
                    <b-button type="reset" variant="danger">Reset</b-button>
            </b-form>
        </b-modal>

        <!--END MODAL FOR COMMENTS -->

        <!--ALERT FOR A PROBLEM YOU HAVE -->

        <b-alert
        variant="warning"
        :show = showDismissibleAlert
        dismissible
        fade
        @dismissed="showDismissibleAlert=false"
        >
        <b>You have this problem</b>
        </b-alert>

        <!--SEARCH ENGINE -->

        <b-row class="justify-content-center" style="margin:0">
            <b-col cols = "3">
                <div role="tablist">
                    <b-card no-body >
                        <b-card-header header-tag="header" class="p-1" role="tab">
                            <b-button block href="#" v-b-toggle.accordion-1 variant="info">Subjects</b-button>
                        </b-card-header>
                        <b-collapse id="accordion-1" visible accordion="my-accordion" role="tabpanel">
                            <b-card-body style="height : 400px; position:relative; overflow-y:scroll">
                                <b-list-group>
                                    <b-list-group-item v-for="(sub, index) of getSubjects" v-bind:key="index" style="padding:0px" > 
                                        <b-button :pressed.sync=sub.state v-if="sub.state== true" @click="selectSubject(sub.name)" variant="light" style="border:0px; width:100%; heigth: 100%">
                                        {{sub.name}}
                                        </b-button>
                                        <b-button :pressed.sync=sub.state v-else @click="selectSubject(sub.name)" variant="light" style="border:0px; width:100%; heigth: 100%" align-h="between">
                                        
                                        <b-row align-h="between">
                                            <b-col cols="9">{{sub.name}}</b-col>
                                            <b-col cols="3"><b-badge variant="primary" pill>X</b-badge></b-col>
                                        </b-row>
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
                                <b-list-group-item v-for="(ty, index) of types" v-bind:key = "index" href="#" variant="info" @click="selectType (index)" >{{ty}}</b-list-group-item>
                            </b-list-group>
                            </b-card-body>
                        </b-collapse>
                    </b-card>
                    <br>
                    <b-card class = "text-center">
                        <p>Créditos</p>
                        <h1> <b>{{creditos}}</b> </h1>
                        <p v-if="creditos <= 0">
                            You can't get new problems, you don't have enough credits
                        </p>
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
                        <div role="tablist" >
                            <b-card style = "margin:20px; background: #d4d4d4" class="mb-1" v-for="(problem, index) of filtrar" v-bind:key = "index">
                                <b-card-title><b>{{problem.title}}</b></b-card-title>
                                <b-row style="height :70px">
                                    <b-col cols = "10" style="height:100%">
                                        <!--b-card-text style="position:relative; overflow-y:hidden">
                                            {{problem.body}}
                                        </b-card-text>-->
                                        <div style="margin-top : 10px">
                                            <b-button variant="info" style = "margin:5px" v-for="(tag, index) of temptaglist" v-bind:key="index">{{tag}}</b-button>
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
                        </div>
                    </b-card-body>
                </b-card>
            </b-col>
        </b-row>

    </div>

</template>



<script>
//import json from './information.json'
import axios from 'axios'
import {mapState} from 'vuex'
export default {
    data :  () => ({

            idselected : 0,

            temptaglist : ["supertag1", "master theorem","ada","algorithms","maths"],
            showDismissibleAlert: false,

            newcomment : '',

            actualUser : 'GiordanoLover777',

            creditos : 3,

            filtrarAvailable : true,

            subjectToSearch : '',

            keyToSearch : '',

            results : [],

            mensajevacio : '',

            totalproblems : [],

            infoproblems : [],

            modal_titleProblem : '',

            modal_desProblem : '',

            modal_tagsProblem : [],

            modal_selectProblem : {},

            selectedSubjects : [],

            subjects : [{'name' : 'Divide and conquer', 'state' : true}, 
                        {'name' : 'Algorithms', 'state' : true},
                        {'name' : 'Maths', 'state' :true}],

            types : {'SA' : 'Short Answer', 'LA' : 'Long Answer', 'MC' : 'Multiple Choice', 'TF': 'True or False'},
            typeSelected : '',

            commentsInfo : [
            ]
        
    }),

    methods: {
        showComment (){
            this.$refs['ModalComment'].show()
        },
        onSubmit(evt) {
            evt.preventDefault()
            this.commentsInfo.push({'author' : this.actualUser, 'text' : this.newcomment})
            this.$refs['ModalComment'].hide()
        },
        onReset(evt) {
            evt.preventDefault()
            
            this.$refs['ModalComment'].hide()
        },
        imprimir : function() {
            for (let problem of this.infoproblems){
                let stringToSearch = problem.tags.toString().concat (" ", problem.body, " ", problem.title).toLowerCase()
                console.log (stringToSearch)
            }
        },

        showModalProblem(index) {
            this.modal_titleProblem = this.infoproblems [index].name
            this.modal_desProblem = this.infoproblems [index].body
            this.modal_tagsProblem = this.infoproblems [index].tags
            this.modal_selectProblem = this.infoproblems [index]
            
            if (this.idsProblems.includes (this.modal_selectProblem.id)){
                this.showDismissibleAlert = true
            }
            else {
                this.$refs['modal-problem'].show()
            }
        },

        hideModalProblem() {
            this.$store.commit ({
                type : 'updateNewProblem',
                valor : this.modal_selectProblem
            })
            this.$store.commit ({
                type : 'updateIds',
                valor : this.modal_selectProblem.id
            })
            this.$store.commit ('updateMyProblems')
            this.$store.commit ('viewProblems')
            this.creditos -= 1;
            this.$refs['modal-problem'].hide()
        },

        cancel () {
            this.$refs['modal-problem'].hide()
        },

        selectSubject (Subject) {
            let intFind = this.selectedSubjects.indexOf(Subject.toLowerCase())
            if (intFind >= 0){
                this.selectedSubjects.splice(intFind, 1)
            }
            else{
                this.selectedSubjects.push (Subject.toLowerCase())
            }
        },

        selectType (Type) {
            if (this.typeSelected === Type)
                this.typeSelected = ''
            else
                this.typeSelected = Type
        },

        writeFile () {
            const fs = require ('fs')
            fs.appendFile('mynewfile1.txt', 'Hello content!', function (err) {
                            if (err) throw err;
                            console.log('Saved!');
                            });
        }

    },

    mounted () {
        axios.get("http://localhost:9898/problem/v1/problem/getProblems")
        .then(response => (this.infoproblems = response.data)),
        axios.get ("https://localhost:9898/problem/v1/problem/getCommentByProblem", {
            params : {
                idProblem : '1'
            }
        })
        .then(response => (this.commentsInfo = response.data))
    },

    computed : {
         ...mapState ({
            problemsAll : state=>state.myProblems,
            idsProblems : state=>state.idsProblems
        }),
        filtrar : function () {
            let res = []
            if(this.infoproblems.length > 0){
                let id = 0
                for (let problem of this.infoproblems) {
                    problem["idx"] = id
                    id = id + 1
                    if (this.selectedSubjects.length === 0)
                    {
                        if (this.keyToSearch === '')
                            {
                                if (this.typeSelected === '' || this.typeSelected === problem.type)
                                        res.push (problem)
                            }
                        else
                            {
                                let stringToSearch = problem.tags.toString().concat (" ", problem.body, " ", problem.title).toLowerCase ()
                                if (stringToSearch.includes (this.keyToSearch.toLowerCase()))
                                {
                                    if (this.typeSelected === '' || this.typeSelected === problem.type)
                                        res.push (problem)
                                }
                            }
                    }
                    else
                    {
                        if (problem.tags.filter(value => this.selectedSubjects.includes (value)).length)
                        {
                            if (this.keyToSearch === '')
                            {
                                if (this.typeSelected === '' || this.typeSelected === problem.type)
                                        res.push (problem)
                            }
                            else
                            {
                                let stringToSearch = problem.tags.toString().concat (" ", problem.body, " ", problem.title).toLowerCase ()
                                if (stringToSearch.includes (this.keyToSearch.toLowerCase()))
                                {
                                    if (this.typeSelected === '' || this.typeSelected === problem.type)
                                        res.push (problem)
                                }
                            }
                        }
                    }
                }
            }

            return res
        },

        getSubjects : function () {
            let finalSubjects = []
            let tempTags = []
            if (this.infoproblems.length > 0){
                for (let problem of this.infoproblems)
                {
                    tempTags = tempTags.concat(problem.tags)
                }
                tempTags = [...new Set(tempTags)]
                for (let tTag of tempTags)
                {
                    finalSubjects.push ({'name' : tTag, 'state' : true})
                }
                finalSubjects.sort(function(a, b){
                    if (a.name > b.name)
                    return 1
                    if (a.name < b.name)
                    return -1
                    return 0
                })
            }
            return finalSubjects
        }
    }


}
</script>
