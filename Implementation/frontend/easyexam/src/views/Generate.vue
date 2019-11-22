<template>
    <div class="md-6 mt-2">
        <div>        
            <center>
            <h1>
                <b v-if="currentPage===1">
                    Select Problems
                </b>
                <b v-else-if="currentPage===2">
                    Exam Details!
                </b>
                <b v-else-if="currentPage===3">
                    Exam Preview
                </b>

                
                
            </h1>
        </center> 
        </div>
 
       
        <b-card-group deck v-if="currentPage===1" align-h="around">
        <b-card header="Your Problems">
            <b-form-input placeholder="Search a problem for your exam" type="text" v-model="keyFromAll">
            </b-form-input>
            <b-card style="margin:20px; background:#d4d4d4" class="mb-1" v-for="(prob, index) of filtrarAll" v-bind:key = "index">
            <b-card-title>
                <b>
                    {{prob.title}}
                </b>
            </b-card-title>
            <b-row class="justify-content-md-center">
                <b-col cols="10">
                    <div style="margin-top:10px">
                        <b-button disabled variant="light" style="margin:5px" v-for="(tag, index) of prob.topicsString" v-bind:key="index">
                            {{tag}}
                        </b-button>
                    </div>

                </b-col>
                <b-col cols="2" style="height:100%">
                    <b-button disabled variant = "light" style=" width : 70px; height:70px"><b>
                        {{prob.type}}</b>
                    </b-button>
                </b-col>
                <b-button style="margin-top : 10px; width:90%"  pill variant="info" @click="SelectProblem(prob.id)">
                    Select Problem
                </b-button>
            </b-row>
        </b-card>
        </b-card>
        <b-card header="Problems Selected">
            <b-form-input style="width:100%" type="text" v-model="keyFromSel" >
            </b-form-input>
            <b-card style="margin:20px; background:#d4d4d4" class="mb-1" v-for="(prob, index) of filtrarSel" v-bind:key = "index">
            <b-card-title>
                <b>
                    {{prob.title}}
                </b>
            </b-card-title>
            <b-row class="justify-content-md-center">
                <b-row style="width:90%">
                <b-col cols="10">
                    <div style="margin-top:10px">
                        <b-button disabled variant="light" style="margin:5px" v-for="(tag, index) of prob.topicsString" v-bind:key="index">
                            {{tag}}
                        </b-button>
                    </div>

                </b-col>
                <b-col cols="2" >
                    <b-form-input  placeholder="a" type="number"></b-form-input>
                </b-col>
                </b-row>
                <b-row style="width:90%">
                    <b-col cols="9">
                        <b-button style="margin-top : 10px ;width:100%" pill variant="info" @click="SaveProblem(prob.id)">
                            Save Problem
                        </b-button>
                    </b-col>
                    <b-col cols="3">
                        <b-button disabled variant = "light" style="margin-top : 10px ; width : 100%"><b>
                            {{prob.type}}</b>
                        </b-button>
                    </b-col>
                </b-row>
                
                
            </b-row>
        </b-card>
        </b-card>
        </b-card-group>    
        <b-card-group deck v-else-if="currentPage===2" >
            <div class="container md-4 w-50 align-center mt-2">            <b-card >
                <small><font color="red">Obligatory *</font> </small>
                <b-form>
                    <b-form-group>
                        <b-form-input
                        v-model="$v.exam.title.$model"
                        placeholder="Title *"
                        :state= "$v.exam.title.$dirty ? !$v.exam.title.$error : null"
                        >
                        </b-form-input>
                        <b-form-invalid-feedback id="input-2-live-feedback">
                            You must need to enter an exam title.
                        </b-form-invalid-feedback>
                    </b-form-group>
                    <b-form-group>
                        <b-form-textarea
                        v-model="$v.exam.indications.$model"
                        :state= "$v.exam.indications.$dirty ? !$v.exam.indications.$error : null"
                        placeholder="Indications *"
                        rows="6">

                        </b-form-textarea>
                    </b-form-group>
                    <b-row>
                        <b-col cols="6">
                            Time:
                            <b-form-input v-model="$v.exam.time.$model"
                            :state= "$v.exam.time.$dirty ? !$v.exam.time.$error : null"
                            >
                            </b-form-input>
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
                </b-card>
            <b-button variant="light" class="mx-1 float-right " @click="submit()" ><i class="fas fa-angle-double-right fa-1x" style="color:  #2f3135 ;"></i></b-button>
        </div>
</b-card-group>
        <b-card-group deck v-else-if="currentPage===3" class="justify-content-md-center">
            <b-card>
                <b-embed
                type="iframe"
                aspect="16by9"
                src=" "
                allowfullscreen
                >
            </b-embed>
            </b-card>

        </b-card-group>
        <b-pagination v-model="currentPage" :total-rows="3" :per-page="1" use-router align="fill" hide-goto-end-buttons></b-pagination>
    </div>
</template>
<script>
import { minLength, required } from 'vuelidate/lib/validators'
import {mapState} from 'vuex'
import { validationMixin } from 'vuelidate'
import axios from 'axios'
export default {
    mixins: [validationMixin],
    data : () => ({
        currentPage : 1,
        keyFromAll : '',
        keyFromSel : '',
        problemsAll : [],
        problemsSelected : [],
        problemsSub : [], 
        componentLoaded: false,
    }),
    mounted() {
        axios.get('http://' + this.$store.state.clientURL + '/problem/v1/problem/getProblemsSubmitedByUser?idUser=' + this.$store.state.user.id)
        .then(response => (this.problemsSub = response.data))
        axios.get('http://' + this.$store.state.clientURL + '/problem/v1/problem/getProblemsSelected?id=' + this.$store.state.user.id)
        .then(response => (this.problemsAll = response.data))
        
        console.log("b");
        console.log(this.problemsAll.length);
        console.log(this.problemsSub.length);
        
    },
    computed: {
        
        ...mapState ({
            //problemsSelected : state=>state.problemsSelected,
            //problemsAll : state=>state.myProblems
        }),
        filtrarAll : function () {
            console.log(this.componentLoaded)
            if(!this.componentLoaded)
                return null;
            console.log("a");
            console.log(this.problemsAll.length);
            console.log(this.problemsSub.length);
            this.$store.commit ('viewProblems')
            this.MergeSelect();
            let res = []
            let id = 0 
                problem["id"] = id
                id = id + 1
                if (this.keyFromAll === '')
                {
                    res.push (problem)
                }
                else
                {
                    let stringToSearch = problem.topicsString.toString().concat (" ", problem.body, " ", problem.title).toLowerCase ()
                    if (stringToSearch.includes (this.keyFromAll.toLowerCase()))
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
                if (this.keyFromSel === '')
                {
                    res.push (problem)
                }
                else
                {
                    let stringToSearch = problem.topicsString.toString().concat (" ", problem.body, " ", problem.title).toLowerCase ()
                    if (stringToSearch.includes (this.keyFromSel.toLowerCase()))
                    {
                        res.push (problem)
                    }
                }
            }
            return res
        }
    },

    methods: {
        SelectProblem : function (index) {
            if (this.problemsSelected.length < 8){
                this.problemsSelected.push (this.problemsAll[index])
                this.problemsAll.splice(index, 1)
            }
        },

        SaveProblem : function (index) {
            this.problemsAll.push (this.problemsSelected[index])
            this.problemsSelected.splice(index, 1)
        },
        MergeSelect(){
        }
    },
    validations: {
    exam: {
        title: {
            required
        },
        indications: {
            minLength: minLength(0)
        },
        time: {
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


