<template>

    <div class="mt-5">

        <!-- START MODAL FOR PROBLEM -->

        <b-modal v-if="available" ref="modal-problem" size = "xl" >
            <template v-slot:modal-title>
                <b>{{modal_titleProblem}}</b>
            </template>
            <b-row>
                <b-col cols = "10">
                    <b-button variant = "info" pill style="margin:3px" v-for="(tag, index) of modal_tagsProblem" v-bind:key = "index">
                    {{tag}}
                    </b-button>
                </b-col>
                <b-col cols = "2">
                    <b-button disabled style="width:100%">
                        {{types[modal_selectProblem ['type']]}}
                    </b-button>
                </b-col>
            </b-row>
            <b-row align-h="around" >
                <b-col cols="9">
                    <b-card no-header style = "width: 95%; margin: 10px; position:relative; overflow-y:scroll">
                        <b-card-body v-html="modal_desProblem">
                        </b-card-body>
                    </b-card>
                    <b-button disabled style="background : #6c757d">
                        Comments
                    </b-button>
                    
                    <ul class="list-unstyled" style="width: 90%; height:200px;position: relative; overflow-y:scroll">
                        <b-media v-for="(com, key) of commentsInfo" v-bind:key = "key" tag="li" style="margin : 10px; width: 90%" >
                            <b-card>
                                <h4><b>{{com.nameTeacher}}</b></h4>
                                <b-card-text>
                                    {{com.description}}
                                </b-card-text>
                            </b-card>
                        </b-media>
                    </ul>
                </b-col>
                <b-col cols="3">
                    <br>
                    <center>
                        <h3>
                            <b style="margin-left: 10px; margin-right: 10px">Score</b>
                        </h3>
                        <b-button variant="success" style="width : 50px; height: 50px; font-size : x-large">
                            {{modal_selectProblem ['score']}}
                        </b-button>
                        <p>
                            {{modal_selectProblem['qualifiers']}} views
                        </p>
                        <b-button-group >
                            <b-button v-for="(btn, idx) in stars"
                                :key="idx"
                                :pressed.sync = "btn.state"
                                variant = "outline-dark"
                                @mouseover= "upstars (idx)"
                                @mouseleave="downstars"
                                @click="updateScore (idx + 1)"
                                pill
                            >
                            <mdb-icon icon="star" />
                            </b-button>
                        </b-button-group>
                    </center>

                </b-col>
            </b-row>
            

            <template v-slot:modal-footer>
                <b-row style="width : 100%">
                    <b-col cols = "4">
                        <center>
                            <b-button variant = "outline-warning" @click="showSol()">
                                <b>See Solution</b>
                            </b-button>
                        </center>
                    </b-col>
                    <b-col cols = "4">
                    <center>
                        <b-button variant="outline-info" @click="showComment()"><b>Comment</b></b-button>
                    </center>
                    </b-col>
                    <b-col cols = "4">
                    <center>
                        <b-button variant="outline-danger" @click="cancel()"><b>Cancel</b></b-button>
                    </center>
                    </b-col>
                </b-row>
            </template>
        </b-modal>

        <!-- MODAL IF YOU DONT HAVE -->

        <b-modal v-else ref="modal-problem" size = "xl" >
            <template v-slot:modal-title>
                <b>{{modal_titleProblem}}</b>
            </template>

            <b-row>
                <b-col cols = "10">
                    <b-button variant = "info" pill style="margin:3px" v-for="(tag, index) of modal_tagsProblem" v-bind:key = "index">
                    {{tag}}
                    </b-button>
                </b-col>
                <b-col cols = "2">
                    <b-button disabled style="width:100%">
                        {{types[modal_selectProblem ['type']]}}
                    </b-button>
                </b-col>
            </b-row>
            <br>
            <b-row align-h="around">
                <b-col cols="10">
                    <b-button disabled style="background : #6c757d">
                        Comments
                    </b-button>
                    
                    <ul class="list-unstyled" style="width: 90%; height:200px;position: relative; overflow-y:scroll">
                        <b-media v-for="(com, key) of commentsInfo" v-bind:key = "key" tag="li" style="margin : 10px; width: 90%" >
                            <b-card>
                                <h4><b>{{com.nameTeacher}}</b></h4>
                                <b-card-text>
                                    {{com.description}}
                                </b-card-text>
                            </b-card>
                        </b-media>
                    </ul>
                </b-col>
                <b-col cols="2">
                    <br>
                    <center>
                        <h3>
                            <b style="margin-left: 10px; margin-right: 10px">Score</b>
                        </h3>
                        <b-button variant="success" style="width : 50px; height: 50px; font-size : x-large">
                            {{modal_selectProblem ['score']}}
                        </b-button>
                        <p>
                            {{modal_selectProblem['qualifiers']}} views
                        </p>
                    </center>
                </b-col>
            </b-row>

            <template v-slot:modal-footer>
                <b-row style="width : 100%">
                    <b-col cols = "6">
                    <center>
                        <b-button variant="outline-warning" @click="hideModalProblem"><b>Get Problem</b></b-button>
                    </center>
                    </b-col>
                    <b-col cols = "6">
                    <center>
                        <b-button variant="outline-danger" @click="cancel()"><b>Cancel</b></b-button>
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
                    <!--b-button type="reset" variant="danger">Reset</b-button-->
            </b-form>
        </b-modal>

        <!--END MODAL FOR COMMENTS -->

        <!-- START MODAL FOR SOLUTION-->

        <b-modal ref="ModalSol" title="Solution " hide-footer>
            {{modal_solution}}
        </b-modal>

        <!-- END MODAL FOR SOLUTION -->

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

        <b-row v-if="$store.state.isLogged == true" class="justify-content-center" style="margin:0">
            <b-col cols = "3">
                <div role="tablist">
                    <b-card no-body >
                        <b-card-header header-tag="header" class="p-1" role="tab">
                            <b-button block href="#" v-b-toggle.accordion-1 variant="info">Subjects</b-button>
                        </b-card-header>
                        <b-collapse id="accordion-1" accordion="my-accordion" role="tabpanel">
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
                            <b-form-input style="width:80%" type="text" v-model="keyToSearch"></b-form-input>
        <b-form-select style="width:10%"
          id="input-3"
          v-model="form_select.tsort"
          :options="form_select.sorts"
          required
        ></b-form-select>
                            <b-button style="width:10%" variant="info">Filtrar</b-button>
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
import { mdbIcon } from 'mdbvue';


export default {

    components : {
        mdbIcon
    },

    data :  () => ({


            showDismissibleAlert: false,

            form_select : {
                tsort : null,
                sorts : [{ text: 'Order by', value: null }, 'Title', 'Score', 'qualifiers'],
            },

            stars : [
                {state : false},
                {state : false},
                {state : false},
                {state : false},
                {state : false}
            ],

            newcomment : '',

            actualUser : 'GiordanoLover777',

            filtrarAvailable : true,

            available : true,

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

            modal_solution : '',

            selectedSubjects : [],

            ifscore : -1,

            mp : [],

            selected : [],

            subjects : [{'name' : 'Divide and conquer', 'state' : true}, 
                        {'name' : 'Algorithms', 'state' : true},
                        {'name' : 'Maths', 'state' :true}],

            types : {'SA' : 'Short Answer', 'LA' : 'Long Answer', 'MC' : 'Multiple Choice', 'TF': 'True or False'},
            typeSelected : '',

            commentsInfo : [],

            idsProblems : []
        
    }),

    methods: {

        checkIfCheck () {
            console.log("checkin")
            console.log(this.modal_selectProblem.id,)
            console.log(this.$store.state.user.id)
            axios.get("http://" + this.$store.state.clientURL + "/problem/v1/problem/getProblemsScore?idProblem="+
            this.modal_selectProblem.id + "&idTeacher="+ this.$store.state.user.id)
            .then (response => this.ifscore = response.data)

        },

        updateScore (val) {
            console.log("up")
            this.checkIfCheck ()
            if (this.ifscore <= 0)
            {
                console.log("al")
                let new_score = this.modal_selectProblem['score']
                new_score = new_score * this.modal_selectProblem ['qualifiers']
                new_score = new_score + val
                new_score = new_score/(this.modal_selectProblem ['qualifiers'] + 1)
                new_score = Math.round (new_score * 10) / 10
                console.log("round")
                console.log(new_score)
                console.log(Math.round(new_score))
                this.modal_selectProblem['qualifiers']++
                this.modal_selectProblem ['score'] = new_score
                axios.post("http://" + this.$store.state.clientURL + "/problem/v1/problem/saveTeacherScore",
                 {id : this.modal_selectProblem.id, idTeacher : this.$store.state.user.id , scoreInteger : val} )
                axios.post("http://" + this.$store.state.clientURL + "/problem/v1/updateProblemRatio?idProblem=" + this.modal_selectProblem.id +
                "&rate=" + val)
            }
        },

        upstars (idx) {
            for (let i = 0; i < idx; i++){
                this.stars[i].state = true
            }
        },

        downstars () {
            for (let i = 0; i < 5; i++){
                this.stars[i].state = false
            }
        },

        showComment (){
            this.$refs['ModalComment'].show()
        },
        showSol () {
            this.$refs['ModalSol'].show()
        },
        onSubmit(evt) {
            evt.preventDefault()
            let co = {'idTeacher' : this.$store.state.user.id,  'nameTeacher' : this.$store.state.user.username, 'description' : this.newcomment, 'idProblem' : this.modal_selectProblem.id}
            this.commentsInfo.push(co)
            axios.post ('http://' + this.$store.state.clientURL + '/comment/v1/submitComment', co)
            this.$refs['ModalComment'].hide()
        },
        onReset(evt) {
            evt.preventDefault()
            
            this.$refs['ModalComment'].hide()
        },
        imprimir : function() {
            for (let problem of this.infoproblems){
                let stringToSearch = problem.topicsString.toString().concat (" ", problem.body, " ", problem.title).toLowerCase()
                console.log (stringToSearch)
            }
        },

        showModalProblem(index) {
            this.modal_titleProblem = this.infoproblems [index].title
            this.modal_desProblem = this.infoproblems [index].body
            this.modal_tagsProblem = this.infoproblems [index].topicsString
            this.modal_selectProblem = this.infoproblems [index]
            axios.get("http://" + this.$store.state.clientURL + "/problem/v1/problem/latexToHtml?idProblem=" + this.modal_selectProblem.id)
            .then(response => {this.modal_desProblem = (response.data)})
            console.log (this.infoproblems[index].body)
            console.log (this.modal_desProblem)
            this.modal_solution = this.infoproblems [index].body
            
            if (this.idsProblems.includes (this.modal_selectProblem.id)){
                this.available = true
                this.showDismissibleAlert = true
                this.$refs['modal-problem'].show()
                axios.get("http://" + this.$store.state.clientURL + "/comment/v1/comment/getCommentByProblem?idProb=" + this.modal_selectProblem.id)
                .then (response => (this.commentsInfo = (response.data)))
            }
            else {
                this.available = false
                this.$refs['modal-problem'].show()
                axios.get("http://" + this.$store.state.clientURL + "/comment/v1/comment/getCommentByProblem?idProb=" + this.modal_selectProblem.id)
                .then (response => (this.commentsInfo = (response.data)))
            }
        },

        hideModalProblem() {
            this.$store.commit ({
                type : 'updateNewProblem',
                valor : this.modal_selectProblem
            })
            this.idsProblems.push (this.modal_selectProblem.id)

            axios.post('http://' + this.$store.state.clientURL + '/problem/v1/problem/saveProblemsSelected', {
                idTeacher : this.$store.state.user.id,
                idProblem : this.modal_selectProblem.id
            })
            let new_credit = this.$store.state.user.credits - 1
            this.$store.state.user.credits -= 1;
            if (this.$store.state.user.credits == 0) alert("You have ran out of credits! Submit a problem to get some more!");
            axios.post('http://' + this.$store.state.clientURL + '/user/v1/teacher/updateBonus',{
                id : this.$store.state.user.id,
                bonus : new_credit
            })

            this.available = true

            //this.$refs['modal-problem'].hide()
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
        axios.get('http://' + this.$store.state.clientURL + '/problem/v1/problem/getProblemsSelected?id=' + this.$store.state.user.id)
        .then(response => {
            this.mp = response.data
            for (let m of response.data){
                this.idsProblems.push(m.id)
            }
        }
        )
        axios.get("http://" + this.$store.state.clientURL +  "/problem/v1/problem/getProblemTopics")
        .then(response => (this.infoproblems = response.data))
        /*axios.get ("http://localhost:9900/comment/v1/comment/getCommentByProblem?idProb=1")
        .then(response => (this.commentsInfo = response.data))*/
//        axios.get("http://" + this.$store.state.clientURL + "/problem/v1/problem/getProblemsSelected")
        
    },

    computed : {
         ...mapState ({
            problemsAll : state=>state.myProblems,
            creditos : state=>state.user.credits
            //idsProblems : state=>state.idsProblems
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
                                let stringToSearch = problem.topicsString.toString().concat (" ", problem.body, " ", problem.title).toLowerCase ()
                                if (stringToSearch.includes (this.keyToSearch.toLowerCase()))
                                {
                                    if (this.typeSelected === '' || this.typeSelected === problem.type)
                                        res.push (problem)
                                }
                            }
                    }
                    else
                    {
                        if (problem.topicsString.filter(value => this.selectedSubjects.includes (value)).length)
                        {
                            if (this.keyToSearch === '')
                            {
                                if (this.typeSelected === '' || this.typeSelected === problem.type)
                                        res.push (problem)
                            }
                            else
                            {
                                let stringToSearch = problem.topicsString.toString().concat (" ", problem.body, " ", problem.title).toLowerCase ()
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

        getSubjects : function () {
            let finalSubjects = []
            let tempTags = []
            if (this.infoproblems.length > 0){
                for (let problem of this.infoproblems)
                {
                    tempTags = tempTags.concat(problem.topicsString)
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
