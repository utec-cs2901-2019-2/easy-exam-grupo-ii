<template>
    <div class="container md-6 align-center mt-2">
        <b-card-group deck>
        <b-card>
            <b-container class="m-2">
                <h1>Submit a Solution!</h1>
                     <font color="red"> <small>Required *</small> </font> 
                        <b-form @reset="onReset">
                            <b-form-group id="input-group-1" label="What type of problem would you like to submit? *" label-for="input-3">
                                <multiselect
                                        v-model="problem.type"
                                        placeholder="Pick a value"
                                        label="name"
                                        track-by="value"
                                        :options="types"
                                        :searchable="false"
                                        :close-on-select="true"
                                        deselectLabel = ""
                                        :allowEmpty = "false"
                                >
                                </multiselect>
                                <b-alert 
                                        :show="dismissCountDownPropType" 
                                        dismissible 
                                        variant="danger"  
                                        @dismissed="dismissCountDownDescrip=0"
                                        @dismiss-count-down="countDownChanged" 
                                        >
                                        You must need to enter a problem solution.
                                </b-alert>
                            </b-form-group>

                            <b-form-group  label="Problem
                            Solution *">
                                <b-form-textarea
                                id="textarea"
                                v-model="$v.solution.description.$model"
                                placeholder="Please enter your solution body here in latex..."
                                :state= "$v.solution.description.$dirty ? !$v.solution.description.$error : null"
                                required
                                rows="6"
                                max-rows="12"
                                ></b-form-textarea>
                                <b-alert 
                                        :show="dismissCountDownDescrip" 
                                        dismissible 
                                        variant="danger"  
                                        @dismissed="dismissCountDownDescrip=0"
                                        @dismiss-count-down="countDownChanged" 
                                        >
                                        You must need to enter a problem solution.
                                </b-alert>
                            </b-form-group>
                            <b-button variant="light" on-clik class = "m-2 float-left" @click = "goBack" ><i class="fas fa-angle-double-left" style="color:  #2f3135;"></i></b-button>
                            <b-button variant = "light" class = "m-2 float-right" v-b-modal.modalPopover>Submit</b-button>
                            <b-button type="reset" variant = "light" class="m-2 float-left" ><i class="fas fa-trash fa-1x" style="color:  #e31d1d;"></i></b-button>
                            <b-button variant="light" class="mx-1 float-right " @click="visualize"><i class="fas fa-play-circle"></i></b-button>
                        </b-form>
                            <b-modal 
                                    title="Submit status"
                                    id="modalPopover"
                                    ok-only
                                >
                                Are you sure you want to submit this problem?
                                <template v-slot:modal-footer>
                                    <b-button
                                        variant="primary"
                                        size="sm"
                                        class="float-right"
                                        type="submit"
                                        @click="onSubmit($event.target)"
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
            </b-container>
        </b-card>
        <b-card>
            <h1>Preview</h1>
            <b-card-body v-html="solution_html">
            </b-card-body>      
        </b-card>
        </b-card-group>

    </div>
</template>

<script>
    import { mapState } from 'vuex'
    import axios from "axios"
    import Multiselect from "vue-multiselect"
    import { validationMixin } from 'vuelidate'
    import { minLength, required } from 'vuelidate/lib/validators'
    import { parse, HtmlGenerator } from 'latex.js'

    export default {
        mixins: [validationMixin],
        name: "Solution",
        data() {
            return {
                dismissSecs: 5,
                dismissCountDownDescrip: 0,
                dismissCountDownPropType: 0,
                tags: [],
                dicty : {'Short Answer' : 'SA', 'Long Answer' : 'LA', 'Multiple Choice' : 'MC' , 'True or False' : 'TF'},
                solution_html: ''
            }
        },
        methods: {
             hideInfo(button){
                this.$root.$emit('bv::hide::modal', 'modalPopover', button)
            },
            onSubmit(evt) {
                let valDescrip = this.solution.description.length > 0 ? true : false;
                 if (valDescrip){
                    const p_post = axios.post("http://localhost:9898/problem/v1/submitProblem", {
                        idTeacher: this.user.id,
                        title: this.problem.title,
                        type: this.problem.type.value,
                        body: this.problem.body,
                        topics: this.problem.topics_id,
                        rutaImage: this.problem.image,
                        descriptionSolution: this.solution.description,
                        pathImageSolution: this.solution.image
                    });
                    p_post.then(resp => {
                        console.log(resp.data)
                    });
                    p_post.catch(error => {
                        console.log(error)
                   });
                   this.hideInfo(evt);
                   this.$router.push('/dashboard');
                   let new_credit = this.$store.state.user.credits + 3
                    this.$store.state.user.credits += 3;
                    axios.post('http://' + this.$store.state.clientURL + '/teacher/v1/teacher/updateBonus',{
                        id : this.$store.state.user.id,
                        bonus : new_credit
                    });
                    this.ResetAllFields();
                    this.goBack();
                } else{
                    this.showAlertDescription()
                }

            },
            onReset(evt) {
                evt.preventDefault();
                this.solution.description = '';
                this.problem.type = '';
            },
            goBack () {
                this.$store.commit('updateViewBack')
            },
            countDownChanged(dismissCountDown) {
                this.dismissCountDown = dismissCountDown
            },
            showAlertDescription() {
                this.dismissCountDownDescrip = this.dismissSecs
            },
            showAlertProbType() {
                this.dismissCountDownPropType = this.dismissSecs
            },
            ResetAllFields(){
                this.solution.description = '';
                this.problem.type = '';
                this.problem.title = '';
                this.problem.body = '';
                this.problem.image = null;
                this.problem.topics_id = []
            },
            visualize(){
                let generator = new HtmlGenerator({ hyphenate: false })
                let doc = parse(this.solution.description, { generator: generator })
                this.solution_html = doc.htmlDocument().documentElement.outerHTML
            }
        },
        mounted() {
            const tag = axios.get("http://" + this.$store.state.clientURL + "/topics/v1/topics/getTopics");
            tag.then(response => (this.tags = response.data));
        },
        computed: {
            ...mapState ({
                problem: state => state.submit.form.problem,
                solution: state => state.submit.form.solution,
                types: state => state.submit.types,
                user: state => state.user,
            }),
            validatImg() {
                return (Boolean(this.problem.image)==true? true: null)
            }
        },
        components: {
            Multiselect

        },
        validations: {
            solution: {
                description: {
                    required,
                    minLength: minLength(1)
                }
            }
        }
    }
</script>

<style scoped>

</style>
