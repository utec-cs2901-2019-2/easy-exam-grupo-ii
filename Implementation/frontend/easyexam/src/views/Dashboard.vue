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
                                    <b-list-group-item v-for="(sub, index) of getSubjects" v-bind:key="index" style="padding:0px" > 
                                        <b-button :pressed.sync=sub.state v-if="sub.state== true" @click="selectSubject(sub.name)" variant="light" style="border:0px; width:100%; heigth: 100%">
                                        {{sub.name}}
                                        </b-button>
                                        <b-button :pressed.sync=sub.state v-else @click="selectSubject(sub.name)" variant="light" style="border:0px; width:100%; heigth: 100%">
                                        {{sub.name}}
                                        <b-badge variant="primary" pill>X</b-badge>
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
                                <b-list-group-item v-for="(ty, index) of types" v-bind:key = "index" href="#" variant="info">{{ty}}</b-list-group-item>
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
                                <b-card-title><b>{{problem.name}}</b></b-card-title>
                                <b-row>
                                    <b-col cols = "10">
                                        <b-card-text style="height : 50px; position:relative; overflow-y:hidden">
                                    {{problem.description}}
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
import json from '/home/juan/Documentos/Software/easy-exam-grupo-ii/Implementation/frontend/easyexam/src/views/information.json'
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

            selectedSubjects : [],

            subjects : [{'name' : 'Divide and conquer', 'state' : true}, 
                        {'name' : 'Algorithms', 'state' : true},
                        {'name' : 'Maths', 'state' :true}],

            types : {'SA' : 'Short Answer', 'LA' : 'Long Answer', 'MC' : 'Multiple Choice', 'TF': 'True or False'}
        
    }),

    methods: {
        imprimir : function() {
            for (let problem of this.infoproblems){
                let stringToSearch = problem.tags.toString().concat (" ", problem.description, " ", problem.name).toLowerCase()
                console.log (stringToSearch)
            }
        },

        showModalProblem(index) {
            this.modal_titleProblem = this.infoproblems [index].name
            this.modal_desProblem = this.infoproblems [index].description
            this.modal_tagsProblem = this.infoproblems [index].tags
            this.modal_selectProblem = this.infoproblems [index]
            
            this.$refs['modal-problem'].show()
            console.log(index)
      },

        selectSubject (Subject) {
            let intFind = this.selectedSubjects.indexOf(Subject.toLowerCase())
            if (intFind >= 0){
                this.selectedSubjects.splice(intFind, 1)
            }
            else{
                this.selectedSubjects.push (Subject.toLowerCase())
                console.log(Subject.toLowerCase())
            }
            console.log("start")
            for (let ip in this.selectedSubjects){
                console.log(this.selectedSubjects[ip])
            }
            /*
            if (this.subjectToSearch === ''){
                this.subjectToSearch = Subject
            }
            else{
                this.subjectToSearch = ''
            }*/
        }

    },

    computed : {
        filtrar : function () {
            let res = []
            
                let id = 0
                for (let problem of this.infoproblems) {
                    problem["id"] = id
                    id = id + 1
                    if (this.selectedSubjects.length === 0)
                    {
                        if (this.keyToSearch === '')
                            {
                                res.push (problem)
                            }
                            else
                            {
                                let stringToSearch = problem.tags.toString().concat (" ", problem.description, " ", problem.name).toLowerCase ()
                                if (stringToSearch.includes (this.keyToSearch.toLowerCase()))
                                {
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
                                res.push (problem)
                            }
                            else
                            {
                                let stringToSearch = problem.tags.toString().concat (" ", problem.description, " ", problem.name).toLowerCase ()
                                if (stringToSearch.includes (this.keyToSearch.toLowerCase()))
                                {
                                    res.push (problem)
                                }
                            }
                        }
                    }
                    /*
                    if (problem.tags.toString().toLowerCase().includes(this.subjectToSearch.toLowerCase()) ) {
                        res.push (problem)
                    }*/
                }
            /*
            else {
                    let id = 0
                    for (let problem of this.infoproblems) {
                        problem["id"] = id
                        id = id + 1
                        let stringToSearch = problem.tags.toString().concat (" ", problem.description, " ", problem.name).toLowerCase()
                        if (stringToSearch.includes (this.keyToSearch.toLowerCase()) && stringToSearch.includes(this.subjectToSearch.toLowerCase()) ) {
                            res.push (problem)
                        }
                    }
            }*/

            return res
        },

        getSubjects : function () {
            let finalSubjects = []
            let tempTags = []
            for (let problem of this.infoproblems)
            {
                tempTags = tempTags.concat(problem.tags)
            }
            tempTags = [...new Set(tempTags)]
            for (let tTag of tempTags)
            {
                finalSubjects.push ({'name' : tTag.charAt(0).toUpperCase() + tTag.slice(1), 'state' : true})
            }
            return finalSubjects
        }
    }


}
</script>