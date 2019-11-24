<template>
    <div class="mt-5 mx-5" >
                <!-- START MODAL FOR PROBLEM -->

        <b-modal ref="modal-problem" size = "xl" >
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
                        <b-button-group v-if="ifscore <= 0">
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
                        <b-button-group v-else>
                            <b-button v-for="n in ifscore"
                                :key="n"
                                pressed
                                variant = "outline-dark"
                                pill
                            >
                            <mdb-icon icon="star" />
                            </b-button>
                            <b-button v-for="n in (5 - ifscore)"
                                :key="n + ifscore"
                                disabled
                                variant = "outline-dark"
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
                        <b-button variant="outline-danger" @click="cancel()"><b>Done</b></b-button>
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
            <div v-html="solutionshow">

            </div>
        </b-modal>

        <!-- END MODAL FOR SOLUTION -->
            <b-row>
                <b-card style="min-width: max-content; max-width: max-content; border-radius: 20px; height: max-content" bg-variant="light">
                    <center>
                        <br>
                            <h2><b>  {{user.information.firstname}} {{user.information.lastname}}</b></h2>

                            <h6 style="color: #6c757d">{{user.information.user.email}}</h6>
                        <h6 style="color: #6c757d">{{user.information.institution}} - {{user.information.country}}</h6>
                        <br><br><br><br>
                        <br>
                        <h4> <b>Problems</b> </h4>
                    <b-row>
                        <b-col style="width: 50%">
                            <h6 style="min-width: max-content"> <b>Submited</b> </h6>
                            <h5 >{{infoproblems.length}}</h5>
                        </b-col>
                        <b-col style="width: 50%">
                            <h6 style="min-width: max-content"><b>Obtained</b></h6>
                            <h5>{{obproblems.length}}</h5>
                        </b-col>
                    </b-row>

                    </center>

                        </b-card>
                    <b-card style="min-width: 70%; margin-left:20px; margin-right: 20px;border-radius: 10px">
                        <b-tabs>
                            <b-tab title="Problems Submited" >
                                <b-row align-h="between" style="margin-top:20px; margin-bottom: 20px; margin-left: 10px">
                                    <b-col >
                                        <b-form-input type="text" v-model="keyToSearch"></b-form-input>
                                    </b-col>
                                    <b-col>
                                        <b-form-select
                                        id="input-3"
                                        v-model="form_select.tsort"
                                        :options="form_select.sorts"
                                        required
                                        ></b-form-select>
                                    </b-col>
                                    <b-col >
                                        <b-button  variant="info">Search</b-button>
                                    </b-col>
                                </b-row>

                                <b-card style = "margin:20px; background: #d4d4d4" class="mb-1" v-for="(problem, index) of filtrar" v-bind:key = "index">
                                    <b-card-title><b>{{problem.title}}</b></b-card-title>
                                    <b-row style="min-height :70px">
                                        <b-col >
                                            <div style="margin-top : 10px">
                                                <b-button variant="info" style = "margin:5px" v-for="(tag, index) of problem.topicsString" v-bind:key="index">{{tag}}</b-button>
                                            </div>
                                        </b-col>
                                        <b-col>
                                            <ul class="list-unstyled">
                                                <li>
                                                    <b-button disabled style="width : 80px">
                                                        {{problem.score}}
                                                    </b-button>
                                                </li>
                                                <li>
                                                    <b-button disabled variant = "light" style="font-size : small; width : 80px"><b>
                                                        {{problem.type}}</b>
                                                    </b-button>
                                                </li>
                                            </ul>
                                        </b-col>
                                    </b-row>
                                    <b-button style="margin-top : 10px" href="#" pill variant="light" @click="showModalProblem1 (problem.idx)">Go problem</b-button>
                                </b-card>
                            </b-tab>

                            <b-tab title="Problems Submited" >
                                <b-row align-h="between" style="margin-top:20px; margin-bottom: 20px; margin-left: 10px">
                                    <b-col >
                                        <b-form-input  type="text" v-model="keyToSearch2"></b-form-input>
                                    </b-col>
                                    <b-col >
                                        <b-form-select
                                        id="input-3"
                                        v-model="form_select.tsort"
                                        :options="form_select.sorts"
                                        required
                                        ></b-form-select>
                                    </b-col>
                                    <b-col >
                                        <b-button variant="info">Search</b-button>
                                    </b-col>
                                </b-row>

                                <b-card style = "margin:20px; background: #d4d4d4" class="mb-1" v-for="(problem, index) of filtrar2" v-bind:key = "index">
                                    <b-card-title><b>{{problem.title}}</b></b-card-title>
                                    <b-row style="min-height :70px">
                                        <b-col >
                                            <div style="margin-top : 10px">
                                                <b-button variant="info" style = "margin:5px" v-for="(tag, index) of problem.topicsString" v-bind:key="index">{{tag}}</b-button>
                                            </div>
                                        </b-col>
                                        <b-col>
                                            <ul class="list-unstyled">
                                                <li>
                                                    <b-button disabled style="width : 80px">
                                                        {{problem.score}}
                                                    </b-button>
                                                </li>
                                                <li>
                                                    <b-button disabled variant = "light" style="font-size : small; width : 80px"><b>
                                                        {{problem.type}}</b>
                                                    </b-button>
                                                </li>
                                            </ul>
                                        </b-col>
                                    </b-row>
                                    <b-button style="margin-top : 10px" href="#" pill variant="light" @click="showModalProblem2 (problem.idx)">Go problem</b-button>
                                </b-card>
                            </b-tab>
                            <b-tab title="Exams Generated"  >
                                <b-row align-h="between" style="margin:auto; border-color: white" >
                                    <b-col sm="5">
                                            <b-button variant="outline-dark" >
                                                <h4><b>Title Exam</b></h4>
                                                <h5># problems</h5>
                                                <h5>
                                                    date
                                                </h5>
                                            </b-button>
                                    </b-col>
                                    <b-col sm="5">
                                            <b-button variant="outline-dark" >
                                                <h4><b>Title Exam</b></h4>
                                                <h5># problems</h5>
                                                <h5>date</h5>
                                            </b-button>
                                    </b-col>
                                </b-row>
                                    <br>
                                <b-row align-h="between" style="margin:auto">
                                        <b-col>
                                            <b-button variant="outline-dark" >
                                                <h4><b>Title Exam</b></h4>
                                                <h5># problems</h5>
                                                <h5>date</h5>
                                            </b-button>
                                        </b-col>
                                        <b-col sm="5">
                                            <b-button variant="outline-dark" >
                                                <h4><b>Title Exam</b></h4>
                                                <h5># problems</h5>
                                                <h5>
                                                    date
                                                </h5>
                                            </b-button>
                                        </b-col>
                                    </b-row>
                                    <br>


                                <b-row align-h="between" style="margin:auto">
                                        <b-col sm=5>
                                            <b-button variant="outline-dark" >
                                                <h4><b>Title Exam</b></h4>
                                                <h5># problems</h5>
                                                <h5>
                                                    date
                                                </h5>
                                            </b-button>
                                        </b-col>
                                        <b-col></b-col>
                                        <b-col sm=5>
                                            <b-button variant="outline-dark" >
                                                <h4><b>Title Exam</b></h4>
                                                <h5># problems</h5>
                                                <h5>
                                                    date
                                                </h5>
                                            </b-button>
                                        </b-col>
                                    </b-row>
                            </b-tab>
                        </b-tabs>
                    </b-card>
            </b-row>
    </div>
</template>

<script>
import axios from 'axios'
import { mdbIcon } from 'mdbvue';

export default {

    components : {
        mdbIcon
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
            },

            modal_titleProblem : '',

            modal_desProblem : '',

            modal_tagsProblem : [],

            modal_selectProblem : {},

            modal_solution : '',

            ifscore : -1,

            solutionshow :  [],

            commentsInfo : [],

            newcomment : '',

            types : {'SA' : 'Short Answer', 'LA' : 'Long Answer', 'MC' : 'Multiple Choice', 'TF': 'True or False'},
            typeSelected : '',

            stars : [
                {state : false},
                {state : false},
                {state : false},
                {state : false},
                {state : false}
            ],

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
        checkIfCheck () {
            axios.get("http://" + this.$store.state.clientURL + "/problem/v1/problem/getProblemsScore?idProblem="+
            this.modal_selectProblem.id + "&idTeacher="+ this.$store.state.user.id)
            .then (response => this.ifscore = response.data)

        },
        showModalProblem1(index) {
            this.modal_titleProblem = this.infoproblems [index].title
            this.modal_desProblem = this.infoproblems [index].body
            this.modal_tagsProblem = this.infoproblems [index].topicsString
            this.modal_selectProblem = this.infoproblems [index]
            this.solutionshow = ''
            axios.get("http://" + this.$store.state.clientURL + "/problem/v1/problem/latexToHtml?idProblem=" + this.modal_selectProblem.id)
            .then(response => {this.modal_desProblem = (response.data)})
            axios.get('http://' + this.$store.state.clientURL + '/problem/v1/problem/getSolutionProblem?idProblem=' + this.modal_selectProblem.id)
            .then(response => (this.solutionshow = (response.data.body)))
            this.modal_solution = this.infoproblems [index].body
            this.checkIfCheck ()
            
                this.$refs['modal-problem'].show()
                axios.get("http://" + this.$store.state.clientURL + "/comment/v1/comment/getCommentByProblem?idProb=" + this.modal_selectProblem.id)
                .then (response => (this.commentsInfo = (response.data)))
        },

        showModalProblem2(index) {
            this.modal_titleProblem = this.obproblems [index].title
            this.modal_desProblem = this.obproblems [index].body
            this.modal_tagsProblem = this.obproblems [index].topicsString
            this.modal_selectProblem = this.obproblems [index]
            this.solutionshow = ''
            axios.get("http://" + this.$store.state.clientURL + "/problem/v1/problem/latexToHtml?idProblem=" + this.modal_selectProblem.id)
            .then(response => {this.modal_desProblem = (response.data)})
            axios.get('http://' + this.$store.state.clientURL + '/problem/v1/problem/getSolutionProblem?idProblem=' + this.modal_selectProblem.id)
            .then(response => (this.solutionshow = (response.data.body)))
            this.modal_solution = this.obproblems [index].body
            this.checkIfCheck ()
            
                this.$refs['modal-problem'].show()
                axios.get("http://" + this.$store.state.clientURL + "/comment/v1/comment/getCommentByProblem?idProb=" + this.modal_selectProblem.id)
                .then (response => (this.commentsInfo = (response.data)))
        },

        updateScore (val) {
            
            if (this.ifscore <= 0)
            {
                let new_score = this.modal_selectProblem['score']
                new_score = new_score * this.modal_selectProblem ['qualifiers']
                new_score = new_score + val
                new_score = new_score/(this.modal_selectProblem ['qualifiers'] + 1)
                new_score = Math.round (new_score * 10) / 10
                this.modal_selectProblem['qualifiers']++
                this.modal_selectProblem ['score'] = new_score
                axios.post("http://" + this.$store.state.clientURL + "/problem/v1/problem/saveTeacherScore",
                 {id : this.modal_selectProblem.id, idTeacher : this.$store.state.user.id , scoreInteger : val} )
                axios.post("http://" + this.$store.state.clientURL + "/problem/v1/updateProblemRatio?idProblem=" + this.modal_selectProblem.id +
                "&rate=" + val)
                this.ifscore = val
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


            axios.post('http://' + this.$store.state.clientURL + '/problem/v1/problem/latexToHtmlbyBody', {
                body: this.solutionshow
            })
            .then(response => (this.solutionshow = (response.data)))

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

        cancel () {
            this.$refs['modal-problem'].hide()
        },

    },
}
</script>