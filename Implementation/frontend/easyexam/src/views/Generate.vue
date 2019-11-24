<template>
    <div class="md-6 mt-2">
        <h1 class="text-center">Generate an Exam</h1>
        {{tabIndex}}
        <b-alert v-model="problemSelectCheck" variant="danger" dismissible>
            You must select at lest one problem for your exam!
        </b-alert>
        <b-alert v-model="richMaximunProblem" variant="warning" dismissible>
            You can select eight problem as maximum for your exam!
        </b-alert>
        <b-alert v-model="examDetailsCheck" variant="danger" dismissible>
            You must enter the required exam details for your exam!
        </b-alert>
        <b-tabs content-class="mt-3" v-model="tabIndex" align="center" fill justified>
            <b-tab title="Select Problems" title-item-class="disabledTab">
                <b-container class="mb-2" style="max-width: 400px;  min-height: 400px;"> 
                    <b-form-input class="w-100" placeholder="Search a problem for your exam" type="text" v-model="keyFromAll"></b-form-input>                
                    <!--Card for problems-->
                    <b-card class="mt-2" v-for="(prob, index) of filtrarAll" v-bind:key = "index" >
                        <b-card-title>{{prob.title}}</b-card-title>
                        <b-card-sub-title><small><strong>Tags: </strong></small><small v-for="(tag, index) of prob.topicsString" v-bind:key="index"> | {{tag}}</small></b-card-sub-title>
                        <b-button squared size="sm" variant="light" class="mt-2 float-md-left" @click="selectProblem(prob.id)">Select</b-button>
                        <b-button squared size="sm" variant="light" class="mt-2 float-md-left">View</b-button>
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
                    </b-form-group>
                    <b-form-group label="Indications:">
                        <b-form-textarea
                        v-model="$v.exam.indications.$model"
                        :state= "$v.exam.indications.$dirty ? !$v.exam.indications.$error : null"
                        placeholder="Indications"
                        rows="6"
                        aria-describedby="input-2-live-feedback"
                        >
                        </b-form-textarea>
                        <b-form-invalid-feedback id="input-2-live-feedback">
                            You must need to enter your exam details.
                        </b-form-invalid-feedback>
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
                            
                        </b-col>
                        <b-col cols="6">
                            Institution:
                            <b-form-input v-model="$v.exam.institution.$model" :state= "$v.exam.institution.$dirty ? !$v.exam.institution.$error : null">
                            </b-form-input>
                        </b-col>
                    </b-row>
                    <b-row>
                        <b-col cols="6">
                            Teacher:
                            <b-form-input v-model="$v.exam.teacher.$model" :state= "$v.exam.teacher.$dirty ? !$v.exam.teacher.$error : null">
                            </b-form-input>
                        </b-col>
                        <b-col cols="6">
                            Course:
                            <b-form-input v-model="$v.exam.course.$model" :state= "$v.exam.course.$dirty ? !$v.exam.course.$error : null">
                            </b-form-input>
                        </b-col>
                    </b-row>
                </b-form>
                    <b-card squared class="mt-2" v-for="(prob, index) of filtrarSel" v-bind:key = "index">
                        <b-button-close squared size="sm" variant="light" class="mt-2 float-md-right" @click="deselectProblem(prob.id)"></b-button-close>
                        <b-card-title>{{prob.title}}</b-card-title>
                        <b-card-text><small class="float-md-right">{{dicty[prob.type]}}</small></b-card-text>
                        <b-form-input style="width: 10px" label="hola"></b-form-input>
                    </b-card>
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
            </b-tab>
            <b-button squared size="sm" variant="light" class="w-50" @click="tabIndex--">Prev</b-button>
            <b-button class="w-50" squared size="sm" variant="light" @click="goNext">Next</b-button>
            <b-progress height="2px" :value="tabIndex+1" :max=3></b-progress>
            
        </b-tabs>
    </div>
</template>
<script>
import { minLength, required } from 'vuelidate/lib/validators'
import {mapState} from 'vuex'
import { validationMixin } from 'vuelidate'
import axios from 'axios'
export default {
    mixins: [validationMixin],
    data(){
        return {
            tabIndex : 0,
            keyFromAll : '',
            keyFromSel : '',
            problemsAll : [],
            problemsSelected : [],
            problemsSub : [], 
            selectOne: false,
            problemSelectCheck: false,
            richMaximunProblem: false,
            examDetailsCheck: false,
            maxNumberProblems: 8,
            dicty : {'SA' : 'Short Answer', 'LA' : 'Long Answer', 'MC' : 'Multiple Choice' , 'TF' : 'True or False'},
        }
    },
    mounted() {
        axios.get('http://' + this.$store.state.clientURL + '/problem/v1/problem/getProblemsSubmitedByUser?idUser=' + this.$store.state.user.id)
        .then(response => (this.problemsAll = this.problemsAll.concat(response.data)))
        axios.get('http://' + this.$store.state.clientURL + '/problem/v1/problem/getProblemsSelected?id=' + this.$store.state.user.id)
        .then(response => (this.problemsAll = this.problemsAll.concat(response.data)))
       
    },
    computed: {
        
        ...mapState ({
            exam: state=>state.exam,
            //problemsSelected : state=>state.problemsSelected,
            //problemsAll : state=>state.myProblems
        }),
        filtrarAll : function () {
            let res = []
            let id = 0
            for (let problem of this.problemsAll) {
                problem["id"] = id
                id = id + 1
                if (this.keyFromAll === '')
                {
                    return res;
                }
                else
                {
                    let stringToSearch = problem.topicsString.toString().concat (" ", problem.body, " ", problem.title).toLowerCase ()
                    if (stringToSearch.includes (this.keyFromAll.toLowerCase()) && res.length<4)
                    {
                        res.push (problem)
                    }
                }
            }
            return res
            
        },

        filtrarSel : function () {
            let res = []
            let id = 0
            for (let problem of this.problemsSelected) {
                problem["id"] = id
                id = id + 1
                if (this.keyFromSel === '' )
                {
                    res.push (problem)
                }
                else
                {
                    let stringToSearch = problem.topicsString.toString().concat (" ", problem.body, " ", problem.title).toLowerCase ()
                    if (stringToSearch.includes (this.keyFromSel.toLowerCase()) )
                    {
                        res.push (problem)
                    }
                }
            }
            return res
        }
    },

    methods: {
        goNext(){
            switch(this.tabIndex){
                case 0:
                    if (this.problemsSelected.length >= 1){
                        this.tabIndex++;
                    }else{
                        this.problemSelectCheck = true;
                    }
                    break;
                case 2:
                    break;
                case 3:
                    break;
                default:
                    this.tabIndex++;
            }
        },
        selectProblem : function (index) {
            if (this.problemsSelected.length < this.maxNumberProblems){
                this.problemsSelected.push (this.problemsAll[index])
                this.problemsAll.splice(index, 1)
            } else{
                this.richMaximunProblem = true;
            }
        },

        deselectProblem : function (index) {
            this.problemsAll.push (this.problemsSelected[index])
            this.problemsSelected.splice(index, 1)
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
        institution: {
            minLength: minLength(0)
        },
        course: {
            required
        },
        teacher: {
            minLength: minLength(0)
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


