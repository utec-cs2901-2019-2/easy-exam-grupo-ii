<template>
    <div class="mt-5">
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

        <br>        

        <b-row v-if="currentPage===1" align-h="around" style="width:100%">
            
            <b-col cols=5>
                <h3>
                    Your Problems:
                </h3>
                <b-card style="width:100%; height:700px" header-tag="header">
                    <template v-slot:header>
                        <b-row align-h="between" style="margin:auto">
                            <b-form-input style="width:100%" type="text" v-model="keyFromAll">
                            </b-form-input>
                        </b-row>
                    </template>

                    <b-card-body style="height:600px; position:relative; overflow-y:scroll">
                        <div role="tablist">
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
                                    <b-button style="margin-top : 10px; width:90%" href="#" pill variant="info" @click="SelectProblem(prob.id)">
                                        Select Problem
                                    </b-button>
                                </b-row>
                            </b-card>
                        </div>
                    </b-card-body>

                </b-card>
            </b-col>

            <b-col cols="5">
                <h3>
                    Problems Selected:
                </h3>
                <b-card style="width:100%; height:700px" header-tag="header">
                    <template v-slot:header>
                        <b-row align-h="between" style="margin:auto">
                            <b-form-input style="width:100%" type="text" v-model="keyFromSel">
                            </b-form-input>
                        </b-row>
                    </template>

                    <b-card-body style="height:600px; position:relative; overflow-y:scroll">
                        <div role="tablist">
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
                                    <b-col cols="2" style="height:100%">
                                        <b-form-input style="width:100%"  type="number"></b-form-input>
                                    </b-col>
                                    </b-row>
                                    <b-row style="width:90%">
                                        <b-col cols="9">
                                            <b-button style="margin-top : 10px ;width:100%" href="#" pill variant="info" @click="SaveProblem(prob.id)">
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
                        </div>
                    </b-card-body>

                </b-card>
            </b-col>
        </b-row>

        

        <b-row v-else-if="currentPage===2" class="justify-content-md-center">
            <b-col col lg="7">
                 <font color="red">Fields with '*' are obligatory</font> 
                <b-form>
                    <b-form-group>
                        <b-form-input
                        v-model="title"
                        placeholder="Title *">

                        </b-form-input>
                    </b-form-group>
                    <b-form-group>
                        <b-form-textarea
                        v-model="text"
                        placeholder="Indications *"
                        rows="6">

                        </b-form-textarea>
                    </b-form-group>
                    <b-row>
                        <b-col cols="6">
                            Time:
                            <b-form-input>
                            </b-form-input>
                        </b-col>
                        <b-col cols="6">
                            Institution:
                            <b-form-input>
                            </b-form-input>
                        </b-col>
                    </b-row>
                    <b-row>
                        <b-col cols="6">
                            Teacher:
                            <b-form-input>
                            </b-form-input>
                        </b-col>
                        <b-col cols="6">
                            Course:
                            <b-form-input>
                            </b-form-input>
                        </b-col>
                    </b-row>
                </b-form>
                </b-col>
        </b-row>

        <b-row v-else-if="currentPage===3" class="justify-content-md-center">
            <b-col col lg="7">
                <div>
  <b-embed
    type="iframe"
    aspect="16by9"
    src=" "
    allowfullscreen
  ></b-embed>
</div>
            </b-col>
        </b-row>

        <br>
        <b-pagination v-model="currentPage" :total-rows="3" :per-page="1" use-router align="fill" hide-goto-end-buttons></b-pagination>
    </div>
</template>

<script>
import {mapState} from 'vuex'
import axios from 'axios'
export default {
    created(){
    this.items = JSON.parse(localStorage.getItem('items'))
    this.$store.state.user.username = this.items.map(items => items.username)
    this.$store.state.user.token = this.items.map(items => items.token)
    this.$store.state.user.credits = this.items.map(items => items.credits)
    this.$store.state.user.id = this.items.map(items => items.id)
    this.$store.state.isLogged = true;
    },

    data : () => ({
        currentPage : 1,
        keyFromAll : '',
        keyFromSel : '',
        problemsAll : []
    }),
    mounted() {
        axios.get('http://' + this.$store.state.clientURL + '/problem/v1/problem/getProblemsSelected?id=' + this.$store.state.user.id)
        .then(response => (this.problemsAll = response.data))
    },
    computed: {
        
        ...mapState ({
            problemsSelected : state=>state.problemsSelected,
            //problemsAll : state=>state.myProblems
        }),
        filtrarAll : function () {
            this.$store.commit ('viewProblems')
            let res = []
            let id = 0
            for (let problem of this.problemsAll) {
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
        }
    },
}
</script>
