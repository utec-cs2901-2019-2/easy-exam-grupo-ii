<template>
    <div class="container md-6 align-center mt-2">
        <b-card>
            <b-container class="m-2">
                <h1>Submit a Solution!</h1>
                        <b-form @reset="onReset">
                            <b-form-group id="input-group-1" label="What type of problem would you like to submit?" label-for="input-3">
                                <multiselect
                                        v-model="problem.type"
                                        placeholder="Pick a value"
                                        label="name"
                                        track-by="id"
                                        :options="types"
                                        :searchable="false"
                                        :close-on-select="true"
                                        deselectLabel = ""
                                        :allowEmpty = "false"
                                >
                                </multiselect>
                            </b-form-group>
                            <b-form-group id="input-group-2" label="Problem Solution" label-for="input-2">
                                <vue-editor
                                        v-model="solution.description"
                                        placeholder="Please enter your solution here"
                                        type="text"
                                        :editor-toolbar="customToolbar"
                                        required
                                >
                                </vue-editor>
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
                                 <b-form-group id = "input-group-img" label="Select an image (optional)">
                                <b-form-file
                                        v-model="solution.image"
                                        :state="validatImg"
                                        placeholder="Choose an image (.jpeg, .png, .gif) or drop it here..."
                                        drop-placeholder="Drop image here..."
                                        accept="image/jpeg, image/png, image/gif"
                                ></b-form-file>
                            </b-form-group>
                            <b-button variant="light" on-clik class = "m-2 float-left" @click = "goBack" ><i class="fas fa-angle-double-left" style="color:  #2f3135;"></i></b-button>
                            <b-button variant = "primary" class = "m-2 float-right" v-b-modal.modalPopover>Submit</b-button>
                            <b-button type="reset" variant = "light" class="m-2 float-left" ><i class="fas fa-trash fa-1x" style="color:  #e31d1d;"></i></b-button>
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
<h2>Visualizador</h2>
                        <b-card class="mt-3" header="Form Data Result">
                            <pre class="m-0">{{ solution }}</pre>
                            <pre class="m-0">{{ problem }}</pre>
                        </b-card>
    </div>
</template>

<script>
    import { VueEditor } from "vue2-editor"
    import { mapState } from 'vuex'
    import axios from "axios"
    import Multiselect from "vue-multiselect"
    import { validationMixin } from 'vuelidate'
    import { minLength, required } from 'vuelidate/lib/validators'
    export default {
        mixins: [validationMixin],
        name: "Solution",
        data() {
            return {
                dismissSecs: 5,
                dismissCountDownDescrip: 0,
                tags: []
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
                        id: new Date(),
                        title: this.problem.title,
                        type: this.problem.type.name,
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
                } else{
                    this.showAlertDescription()
                }
            },
            onReset(evt) {
                evt.preventDefault();
                this.solution.description = '';
            },
            goBack () {
                this.$store.commit('updateViewBack')
            },
            countDownChanged(dismissCountDown) {
                this.dismissCountDown = dismissCountDown
            },
            showAlertDescription() {
                this.dismissCountDownDescrip = this.dismissSecs
            }
        },
        mounted() {
            const tag = axios.get("http://localhost:9898/topics/v1/topics/getTopics");
            tag.then(response => (this.tags = response.data));
        },
        computed: {
            ...mapState ({
                problem: state => state.submit.form.problem,
                solution: state => state.submit.form.solution,
                types: state => state.submit.types,
                customToolbar: state => state.submit.editor
            }),
            validatImg() {
                return (Boolean(this.problem.image)==true? true: null)
            }
        },
        components: {
            VueEditor,
            Multiselect

        },
        validations: {
            solution: {
                description: {
                    required,
                    minLength: minLength(10)
                }
            }
        }
    }
</script>

<style scoped>

</style>