<template>
    <div class="mt-5 mx-5">
      <!-- START MODAL FOR PROBLEM -->
        <b-modal ref="modal-problem" size="lg" >
            <template v-slot:modal-title>
                <b>{{modal_titleProblem}}</b>
            </template>
            <b-container>
                <b-row>
                    <b-col><b-card-sub-title><small><strong>Tags: </strong></small><small v-for="(tag, index) of modal_tagsProblem" v-bind:key="index"> | {{tag}}</small></b-card-sub-title></b-col>
                    <b-col><b-card-sub-title><small class="float-right"><strong>Type: </strong>{{types[modal_selectProblem ['type']]}}</small></b-card-sub-title></b-col>
                </b-row>
            </b-container>  
            <b-card-body v-html="modal_desProblem"></b-card-body>
            <b-row>
                <b-col cols="6" sm="8"><b-card header="Comments">
                    <b-card v-for="(com, key) of commentsInfo" v-bind:key ="key">
                        <b-card-sub-title>{{com.nameTeacher}}</b-card-sub-title>
                        <b-card-text>{{com.description}}</b-card-text>
                    </b-card>
                </b-card></b-col>
                <b-col cols="6" sm="4">
                    <b-container class="float-right border border-light">
                        <div class="text-center">Score<br><b-button disabled class="pull-right">{{modal_selectProblem ['score']}}</b-button></div>
                        <br>
                        <div class="text-center"><strong>{{modal_selectProblem['qualifiers']}} </strong>qualifiers</div>
                        <center>
                        <b-button-group v-if="tempscore > 0">
                            <b-button size="sm" v-for="(btn, idx) in stars"
                                :key="idx"
                                :pressed.sync = "btn.state"
                                variant = "outline-dark"
                                @click="updateScore (idx + 1)"
                                pill
                                v-b-popover.hover.top="'You can change the rating until you press Done'"
                            >
                            <mdb-icon icon="star" />
                            </b-button>
                        </b-button-group>
                        <b-button-group v-else-if="ifscore <= 0">
                            <b-button size="sm" v-for="(btn, idx) in stars"
                                :key="idx"
                                :pressed.sync = "btn.state"
                                variant = "outline-dark"
                                @mouseover= "upstars (idx)"
                                @mouseleave="downstars"
                                @click="updateScore (idx + 1)"
                                pill
                                v-b-popover.hover.top="'You can change the rating until you press Done'"
                            >
                            <mdb-icon icon="star" />
                            </b-button>
                        </b-button-group>
                        <b-button-group v-else>
                            <b-button size="sm" v-for="n in ifscore"
                                :key="n"
                                pressed
                                variant = "outline-dark"
                                pill
                            >
                            <mdb-icon icon="star" />
                            </b-button>
                            <b-button size="sm" v-for="n in (5 - ifscore)"
                                :key="n + ifscore"
                                disabled
                                variant = "outline-dark"
                                pill
                            >
                            <mdb-icon icon="star" />
                            </b-button>
                        </b-button-group>
                        </center>
                    </b-container>                  
                </b-col>
            </b-row>
            <template v-slot:modal-footer>
                <b-row v-if="!isReport" style="width : 100%">
                    <b-col cols = "3">
                        <center>
                            <b-button @click="showSol()">
                                <b>See Solution</b>
                            </b-button>
                        </center>
                    </b-col>
                    <b-col cols = "3">
                    <center>
                        <b-button variant="outline-info" @click="showComment()"><b>Comment</b></b-button>
                    </center>
                    </b-col>
                    <b-col cols = "3">
                        <center>
                            <b-button variant="outline-danger" @click="reportProblem()">
                                <b>Report</b>
                            </b-button>
                        </center>
                    </b-col>
                    <b-col cols = "3">
                    <center>
                        <b-button variant="outline-danger" @click="cancel()"><b>Done</b></b-button>
                    </center>
                    </b-col>
                </b-row>
                <b-row v-else style="width : 100%">
                    <b-col cols = "4">
                        <center>
                            <b-button @click="showSol()">
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

        <!-- START MODAL FOR REPORT -->

        <b-modal ref="ModalReport" title="Report" hide-footer>
            <b-form @submit="reportSubmit">
                <b-form-group
                    id="input-group-1"
                    label="Write a comment:"
                    label-for="input-1"
                >
                    <b-form-textarea
                    id="textarea-state"
                    v-model="reportcomment"
                    :state="reportcomment.length <= 500"
                    placeholder="Enter a maximun of 500 characters"
                    rows="5"
                    ></b-form-textarea>

                </b-form-group>
                    <b-button type="submit" variant="danger">Submit</b-button>
                    <!--b-button type="reset" variant="danger">Reset</b-button-->
            </b-form>
        </b-modal>

        <!-- END MODAL FOR REPORT -->


        <!-- START MODAL FOR SOLUTION-->

        <b-modal ref="ModalSol" title="Solution " hide-footer>
            <div v-html="solutionshow">

            </div>
        </b-modal>

        <!-- END MODAL FOR SOLUTION -->

        <div class="mx-5">
            <b-row>
                <b-card style="min-width: max-content; max-width: max-content; border-radius: 15px; height: max-content" bg-variant="light">
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

                <b-col cols="12" md="8">
                    <div class="w-100 h-100">
                        <b-tabs justified>
                            <!--b-tab class="p-4" title="Overview" active>

                            </b-tab-->
                            <b-tab class="p-4 h-100" title="Problems Submited">
                                <b-row align-h="between" style="margin:auto">
                                    <b-col cols="12" lg="8" class="p-0">
                                        <b-form-input class="w-100" type="text" v-model="keyToSearch"></b-form-input>
                                    </b-col>
                                    <b-col cols="12" lg="2" class="p-0">
                                        <b-form-select class="w-100"
                                        id="input-3"
                                        v-model="form_select.tsort"
                                        :options="form_select.sorts"
                                        required
                                        ></b-form-select>
                                    </b-col>
                                    <b-col cols="12" lg="2" class="p-0">
                                        <b-button class="w-100">Search</b-button>
                                    </b-col>
                                </b-row>
                                <b-container> 
                                    <!--Card for problems-->
                                    <b-card class="mt-2 shadow-sm" v-for="(problem, index) of filtrar" v-bind:key = "index" >
                                        <b-button disabled size="sm" variant="light" class="float-right"><small><strong>Score</strong></small><br><small class="pull-right">{{problem.score}}</small></b-button>
                                        <b-card-title>{{problem.title}}</b-card-title>
                                        <b-card-sub-title><small><strong>Tags: </strong></small><small v-for="(tag, index) of problem.topicsString" v-bind:key="index"> | {{tag}}</small></b-card-sub-title>
                                        <b-button size="sm" variant="light" class="mt-2 float-md-left" @click="showModalProblem1 (problem.idx)">View</b-button>
                                        <b-card-text><small class="float-right">{{dicty[problem.type]}}</small></b-card-text>
                                    </b-card>
                                </b-container>
                            </b-tab>
                            <b-tab class="p-4 h-100" title="Problems Obtained">
                                <b-row align-h="between" style="margin:auto" fixed>
                                    <b-col cols="12" lg="8" class="p-0">
                                        <b-form-input class="w-100" type="text" v-model="keyToSearch2"></b-form-input>
                                    </b-col>
                                    <b-col cols="12" lg="2" class="p-0">
                                        <b-form-select class="w-100"
                                        id="input-3"
                                        v-model="form_select.tsort"
                                        :options="form_select.sorts"
                                        required
                                        ></b-form-select>
                                    </b-col>
                                    <b-col cols="12" lg="2" class="p-0">
                                        <b-button class="w-100">Search</b-button>
                                    </b-col>
                                </b-row>

                                <b-container> 
                                    <!--Card for problems-->
                                    <b-card class="mt-2 shadow-sm" v-for="(problem, index) of filtrar2" v-bind:key = "index" >
                                        <b-button disabled size="sm" variant="light" class="float-right"><small><strong>Score</strong></small><br><small class="pull-right">{{problem.score}}</small></b-button>
                                        <b-card-title>{{problem.title}}</b-card-title>
                                        <b-card-sub-title><small><strong>Tags: </strong></small><small v-for="(tag, index) of problem.topicsString" v-bind:key="index"> | {{tag}}</small></b-card-sub-title>
                                        <b-button size="sm" variant="light" class="mt-2 float-md-left" @click="showModalProblem2 (problem.idx)">View</b-button>
                                        <b-card-text><small class="float-right">{{dicty[problem.type]}}</small></b-card-text>
                                    </b-card>
                                </b-container>
                            </b-tab>
                            <b-tab class="p-4" title="Exams" @click="getExamsGenerated">

                                <b-container>
                                    <b-card class="mt-2 shadow-sm" v-for="exam in examList" v-bind:key = "exam.id" >
                                        <b-button disabled size="sm" variant="light" class="float-right"><small><strong>Date</strong></small><br><small class="pull-right">{{exam.creationDate}}</small></b-button>
                                        <b-card-title>{{exam.title}}</b-card-title>
                                        <b-card-sub-title><small><strong>Course: </strong></small><small>{{exam.course}}</small></b-card-sub-title>
                                    </b-card>
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
import { mdbIcon } from 'mdbvue';
import { parse, HtmlGenerator } from 'latex.js'
import katex from 'katex';
import 'katex/dist/katex.min.css';

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
            examList: [],
            infoproblems : [],
            obproblems : [],
            keyToSearch : '',
            keyToSearch2 : '',
            show : true,
            dicty : {'SA' : 'Short Answer', 'LA' : 'Long Answer', 'MC' : 'Multiple Choice' , 'TF' : 'True or False'},
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

            tempscore : -1,

            reportcomment : '',

            isReport : false,

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
        window.katex = katex;
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
        checkIsReported () {
            axios.get ('http://' + this.$store.state.clientURL + '/suggest/v1/isReported?idProblem=' + this.modal_selectProblem.id
            + '&idTeacher=' + this.$store.state.user.id)
            .then (response => {
                this.isReport = response.data
            })
        },
        getExamsGenerated(){
            axios.get("http://" + this.$store.state.clientURL + "/exam/v1/exam/getExams?idTeacher=" + this.$store.state.user.id)
            .then (response => {
                this.examList = response.data
                
            })
        },
        showModalProblem1(index) {
            this.modal_titleProblem = this.infoproblems [index].title
            this.modal_desProblem = this.infoproblems [index].body
            this.modal_tagsProblem = this.infoproblems [index].topicsString
            this.modal_selectProblem = this.infoproblems [index]
            this.solutionshow = ''
            this.tempscore = -1
            this.checkIsReported()
            
            let generator = new HtmlGenerator({ hyphenate: false })
            let doc = parse(this.infoproblems [index].body, { generator: generator })
            this.modal_desProblem = doc.htmlDocument().documentElement.outerHTML;

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
            this.tempscore = -1
            this.checkIsReported ()

            let generator = new HtmlGenerator({ hyphenate: false })
            let doc = parse(this.obproblems [index].body, { generator: generator })
            this.modal_desProblem = doc.htmlDocument().documentElement.outerHTML;

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
                this.downstars ()
                this.upstars (val)
                this.tempscore = val
                /*let new_score = this.modal_selectProblem['score']
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
                this.ifscore = val*/
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

        reportProblem (){
            this.$refs['ModalReport'].show()
        },

        showSol () {
            let generator = new HtmlGenerator({ hyphenate: false })
            let doc = parse(this.solutionshow, { generator: generator })
            this.solutionshow = doc.htmlDocument().documentElement.outerHTML;
            this.$refs['ModalSol'].show()
        },
        onSubmit(evt) {
            evt.preventDefault()
            let co = {'idTeacher' : this.$store.state.user.id,  'nameTeacher' : this.$store.state.user.username, 'description' : this.newcomment, 'idProblem' : this.modal_selectProblem.id}
            this.commentsInfo.push(co)
            axios.post ('http://' + this.$store.state.clientURL + '/comment/v1/submitComment', co)
            this.$refs['ModalComment'].hide()

            this.newcomment = ''
        },
        onReset(evt) {
            evt.preventDefault()
            
            this.$refs['ModalComment'].hide()
        },

        reportSubmit (evt){
            evt.preventDefault()
            axios.post ('http://' + this.$store.state.clientURL + '/suggest/v1/reportProblem?comment=' + this.reportcomment + '&idProblem=' + 
            this.modal_selectProblem.id + '&idTeacher=' + this.$store.state.user.id)
            this.$refs['ModalReport'].hide()
            this.reportcomment = ''
            this.isReport = true
        },

        cancel () {
            if (this.tempscore > 0){
                let val = this.tempscore
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
            this.tempscore = -1
            this.isReport = false
            this.$refs['modal-problem'].hide()
        },

    },
}
</script>