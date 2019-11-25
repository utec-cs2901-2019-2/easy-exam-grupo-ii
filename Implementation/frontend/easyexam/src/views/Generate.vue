<template>
    <div class="md-6 mt-2">
        <h1 class="text-center">Generate an Exam</h1>
        <b-alert 
            variant="danger" dismissible
            :show="dismissCountDownSelect"
            @dismissed="dismissCountDownSelect=0"
            @dismiss-count-down="countDownChanged" 
        >
            You must select at lest one problem for your exam!
        </b-alert>
        <b-alert v-model="richMaximunProblem" variant="warning" dismissible>
            You can select eight problem as maximum for your exam!
        </b-alert>
        <b-button squared size="sm" variant="light" class="w-50" @click="tabIndex--"><i class="fas fa-angle-double-left fa-1x" style="color:  #2f3135 ;"></i></b-button>
            <b-button class="w-50" squared size="sm" variant="light" @click="goNext"><i class="fas fa-angle-double-right fa-1x" style="color:  #2f3135 ;"></i></b-button> 
            <b-progress height="2px" :value="tabIndex+1" :max=3></b-progress>
        <b-tabs content-class="mt-3" v-model="tabIndex" align="center" fill justified>
            <b-tab title="Select Problems" title-item-class="disabledTab">
                <b-container class="mb-2" style="max-width: 600px;  min-height: 400px;"> 
                    <b-form-input class="w-100" placeholder="Search a problem for your exam" type="text" v-model="keyFromAll"></b-form-input>                
                    <!--Card for problems-->
                    <b-card class="mt-2 shadow-sm" v-for="(prob, index) of filtrarAll" v-bind:key = "index" >
                        <b-card-title>{{prob.title}}</b-card-title>
                        <b-card-sub-title><small><strong>Tags: </strong></small><small v-for="(tag, index) of prob.topicsString" v-bind:key="index"> | {{tag}}</small></b-card-sub-title>
                        <b-button squared size="sm" variant="light" class="mt-2 float-md-left" @click="selectProblem(prob.idx)">Select</b-button>
                        <b-button squared size="sm" variant="light" class="mt-2 float-md-left" @click="visualizeModal(prob.body);$bvModal.show('problemVisualizador');">View</b-button>
                        <b-card-text><small class="float-right">{{dicty[prob.type]}}</small></b-card-text>
                    </b-card>
                </b-container>
                
            </b-tab>
            <b-tab title="Exam Details" title-item-class="disabledTab">
                <b-container class="mb-2" style="max-width: 600px;">
                    <small><font color="red">Obligatory *</font> </small>
                    <b-form>
                    <b-form-group label = "Title: *">
                        <b-form-input
                        v-model="$v.exam.title.$model"
                        placeholder="Title"
                        :state= "$v.exam.title.$dirty ? !$v.exam.title.$error : null"
                        aria-describedby="input-1-live-feedback"
                        >
                        </b-form-input>
                        <b-form-invalid-feedback id="input-1-live-feedback">
                            You must need to enter your exam title.
                        </b-form-invalid-feedback>
                        <b-alert 
                            :show="dismissCountDownTitle"
                            @dismissed="dismissCountDownTitle=0"
                            @dismiss-count-down="countDownChanged"  
                            variant="danger" dismissible>
                            You must enter the title for your exam!
                        </b-alert>
                    </b-form-group>
                    <b-form-group label="Indications: *">
                        <b-form-textarea
                        v-model="$v.exam.indications.$model"
                        :state= "$v.exam.indications.$dirty ? !$v.exam.indications.$error : null"
                        placeholder="Indications"
                        rows="6"
                        aria-describedby="input-2-live-feedback"
                        >
                        </b-form-textarea>
                        <b-form-invalid-feedback id="input-2-live-feedback">
                            You must need to enter your exam indications.
                        </b-form-invalid-feedback>
                        <b-alert 
                            :show="dismissCountDownIndications"
                            @dismissed="dismissCountDownIndications=0"
                            @dismiss-count-down="countDownChanged" 
                        variant="danger" dismissible>
                            You must enter the indications for your exam!
                        </b-alert>
                    </b-form-group>
                    <b-row>
                        <b-col cols="6">
                            Duration: *
                            <b-form-input v-model="$v.exam.duration.$model"
                            :state= "$v.exam.duration.$dirty ? !$v.exam.duration.$error : null"
                            aria-describedby="input-3-live-feedback"
                            >
                            </b-form-input>
                            <b-form-invalid-feedback id="input-3-live-feedback">
                            You must need to enter your exam details.
                            </b-form-invalid-feedback>
                            <b-alert 
                                :show="dismissCountDownDuration"
                                @dismissed="dismissCountDownDuration=0"
                                @dismiss-count-down="countDownChanged" 
                                variant="danger" dismissible>
                                You must enter the title for your exam!
                            </b-alert>   
                        </b-col>
                        <b-col cols="6">
                            Course:*
                            <b-form-input v-model="$v.exam.course.$model"
                            :state= "$v.exam.course.$dirty ? !$v.exam.course.$error : null"
                            aria-describedby="input-4-live-feedback"
                            >
                            </b-form-input>
                            <b-form-invalid-feedback id="input-4-live-feedback">
                            You must need to enter your exam details.
                            </b-form-invalid-feedback>
                            <b-alert 
                                :show="dismissCountDownCourse"
                                @dismissed="dismissCountDownCourse=0"
                                @dismiss-count-down="countDownChanged" 
                                variant="danger" dismissible>
                                You must enter the course for your exam!
                            </b-alert> 
                        </b-col>
                    </b-row>
                    <b-form-group label = "Add your problems score:" class="mt-2">                   
                    <b-container fluid>
                        <!-- Main table element -->
                        <b-table
                        show-empty
                        small
                        stacked="md"
                        :items="problemsSelected"
                        :fields="fields"      
                        >
                        {
                        <template v-slot:cell(type)="row">
                            {{ dicty[row.value] }}
                        </template>
                        <template v-slot:cell(deselect)="row">
                            <b-button-close squared size="sm" variant="light" class="mt-2 float-md-right" @click="deselectProblem(row.item.idx)"></b-button-close>
                        </template>

                        <template v-slot:cell(score)="row">
                            <input style="width: 30px;" v-model="row.value" v-on:keyup.enter="updateScore(row.item.id, row.value)" placeholder="edit me">
                        </template>

                        
                        <template v-slot:cell(problem)="row">
                            <b-button variant="light" size="sm" @click="row.toggleDetails">
                            {{ row.detailsShowing ? 'Hide' : 'Show' }} Problem
                            </b-button>
                        </template>

                        <template v-slot:row-details="row">
                            <b-card>
                                <b-card-body v-html="visualizeCard(row.item.body)">
                                </b-card-body>
                            </b-card>
                        </template>
                        </b-table>

                    </b-container>
                    </b-form-group>
                </b-form>
                    
                </b-container>
            </b-tab>
            <b-tab title="Exam Preview"  title-item-class="disabledTab">
                <b-container style="height: 400px; max-height: 600px;">
                    <b-embed
                    type="iframe"
                    aspect="16by9"
                    src=" "
                    allowfullscreen
                    >
                    </b-embed>
                </b-container>
                <b-button @click="generateExam">Generate Exam</b-button>
            </b-tab>
            
            
        </b-tabs>

        <!--Modal Visualizador-->
            <b-modal title="Problem Vizualizador" id="problemVisualizador" ok-only>
            <b-card-body v-html="problem_html"></b-card-body>
            </b-modal>
        <!--Modal Submit Confirmation-->
        <b-modal 
                title="Submit status"
                id="examSubmitModal"
                ok-only
            >
            Are you sure you want to submit this exam?
            <template v-slot:modal-footer>
                <b-button
                    variant="primary"
                    size="sm"
                    class="float-right"
                    type="submit"
                    @click="submitExam($event.target)"
                >
                Yes
                </b-button>
                <b-button
                    variant="danger"
                    size="sm"
                    class="float-left"
                    @click="hideInfo($event.target)"
                >
                Cancel
                </b-button>
            </template>
            </b-modal>
    </div>
</template>
<script>
import { minLength, required } from 'vuelidate/lib/validators'
import {mapState} from 'vuex'
import { validationMixin } from 'vuelidate'
import { parse, HtmlGenerator } from 'latex.js'
import katex from 'katex';
import 'katex/dist/katex.min.css';
import axios from 'axios'
export default {
    mixins: [validationMixin],
    data(){
        return {
            dismissSecs: 5,
            dismissCountDownDuration: 0,
            dismissCountDownCourse: 0,
            dismissCountDownIndications: 0,
            dismissCountDownSelect: 0,
            dismissCountDownTitle: 0,
            tabIndex : 0,
            keyFromAll : '',
            keyFromSel : '',
            problemsAll : [],
            problemsSelected : [],
            problemsSelectedCompleted: [],
            problemsSub : [], 
            selectOne: false,
            richMaximunProblem: false,
            maxNumberProblems: 8,
            problem_html: '',
            dicty : {'SA' : 'Short Answer', 'LA' : 'Long Answer', 'MC' : 'Multiple Choice' , 'TF' : 'True or False'},
            fields: [
                {key: 'title', label:'Title'},
                {key: 'type', label: 'Type'},
                {key: 'score', label: 'Score'},
                {key: 'problem', label: 'Problem'},
                {key: 'deselect', label: 'Deselect'}
            ]
        }
    },
    mounted() {
        window.katex = katex;
        axios.get('http://' + this.$store.state.clientURL + '/problem/v1/problem/getProblemsSubmitedByUser?idUser=' + this.$store.state.user.id)
        .then(response => (this.problemsAll = this.problemsAll.concat(response.data)))
        axios.get('http://' + this.$store.state.clientURL + '/problem/v1/problem/getProblemsSelected?id=' + this.$store.state.user.id)
        .then(response => (this.problemsAll = this.problemsAll.concat(response.data)))
       
    },
    computed: {
        
        ...mapState ({
            exam: state=>state.exam,
            user: state=>state.user,
            //problemsSelected : state=>state.problemsSelected,
            //problemsAll : state=>state.myProblems
        }),
        filtrarAll : function () {
            let res = []
            let id = 0
            for (let problem of this.problemsAll) {
                problem["idx"] = id
                id = id + 1
                if (this.keyFromAll === ''){
                    res.push(problem);
                }else{
                    let stringToSearch = problem.topicsString.toString().concat (" ", problem.body, " ", problem.title).toLowerCase ()
                    if (stringToSearch.includes (this.keyFromAll.toLowerCase()))
                    {
                        res.push (problem)
                    }
                }
            }
            return res
            
        },
    },

    methods: {
        hideInfo(button){
            this.$root.$emit('bv::hide::modal', 'examSubmitModal', button)
        },
        visualizeCard(body){
            let generator = new HtmlGenerator({ hyphenate: false })
            let doc = parse(body, { generator: generator })
            return doc.htmlDocument().documentElement.outerHTML
        },
        visualizeModal(body){
            let generator = new HtmlGenerator({ hyphenate: false })
            let doc = parse(body, { generator: generator })
            this.problem_html = doc.htmlDocument().documentElement.outerHTML;
        },
        submitExam(evt){

            const p_post = axios.post('http://' + this.$store.state.clientURL +'/exam/v1/submitExam', {
                idTeacher: this.user.id,
                title: this.exam.title,
                course: this.exam.course,
                listProblems: this.problemsSelected,
                duration: this.exam.duration,
                indications: this.exam.indications
            });
            p_post.then(resp => {
                console.log(resp.data)
            });
            p_post.catch(error => {
                console.log(error)
            });
            this.hideInfo(evt);
            this.tabIndex++;
        },
        generateExam(){
            axios({
                url: 'http://' + this.$store.state.clientURL +'/exam/v1/generateExam?idExam=1&idTeacher=1',
                method: 'GET',
                responseType: 'blob',
            }).then((response) => {
                var fileURL = window.URL.createObjectURL(new Blob([response.data]));
                var fileLink = document.createElement('a');              
                fileLink.href = fileURL;
                fileLink.setAttribute('download', 'easyexam.pdf');
                document.body.appendChild(fileLink);
                fileLink.click();
            });
        },
        goNext(){
            switch(this.tabIndex){
                case 0:{
                    if (this.problemsSelected.length >= 1){
                        this.tabIndex++;
                    }else{
                        this.showAlertSelect();
                    }
                    break;
                }
                case 1:{
                    let valTitle = this.exam.title.length>0;
                    let valDuration = this.exam.duration.length>0;
                    let valIndications = this.exam.indications.length>=20;
                    let valCourse = this.exam.course.length>0;
                    if (valTitle && valDuration && valIndications && valCourse){
                        this.$bvModal.show('examSubmitModal');
                    }else{
                    if (!valIndications){
                        this.showAlertIndicationsMissing();
                    }if (!valDuration){
                        this.showAlertDurationMissing();
                    }if (!valTitle){
                        this.showAlertTitleMissing();
                    }if (!valCourse){
                        this.showAlertCourseMissing();
                    }
                    }
                    break;
                }
                default:
                    this.tabIndex++;
            }
        },
        updateScore(index, newScore){
            console.log("updated")
            console.log(index)
            console.log(newScore)
            this.problemsSelected[index].score = newScore;
        },
        selectProblem : function (index) {
            console.log(index)
            if (this.problemsSelectedCompleted.length < this.maxNumberProblems){
                let newProblem = {
                    id: this.problemsAll[index].id,
                    type: this.problemsAll[index].type,
                    score: 0,
                    title: this.problemsAll[index].title,
                    body: this.problemsAll[index].body,
                    idx : this.problemsSelected.length
                }
                this.problemsSelectedCompleted.push (this.problemsAll[index])
                this.problemsSelected.push (newProblem)
                this.problemsAll.splice(index, 1)
            } else{
                this.richMaximunProblem = true;
            }
        },
        deselectProblem : function (index) {
            console.log(index)
            this.problemsAll.push (this.problemsSelectedCompleted[index])
            this.problemsSelected.splice(index, 1)
            this.problemsSelectedCompleted.splice(index, 1)
        },
        countDownChanged(dismissCountDown) {
                this.dismissCountDown = dismissCountDown
        },
        showAlertSelect() {
            this.dismissCountDownSelect = this.dismissSecs
        },
        showAlertTitleMissing() {
            this.dismissCountDownTitle = this.dismissSecs
        },
        showAlertIndicationsMissing() {
            this.dismissCountDownIndications = this.dismissSecs
        },
        showAlertDurationMissing() {
            this.dismissCountDownDuration = this.dismissSecs
        },
        showAlertCourseMissing() {
            this.dismissCountDownCourse = this.dismissSecs
        },
    },
    validations: {
    exam: {
        title: {
            required
        },
        indications: {
            required,
            minLength: minLength(20)
        },
        duration: {
            required
        },
        course: {
            required
        }

    }
}
}
</script>
<style>
  .disabledTab{
      pointer-events: none;
  }
</style>


