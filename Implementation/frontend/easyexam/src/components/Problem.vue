<template>
    <div class="container md-6 align-center mt-2">
        <b-card>
            <b-container class="m-2">
                <h1>Submit a Problem!</h1>

                        <b-form @reset="onReset">
                            <b-form-group id="input-group-2" label="Problem Title:">
                                <b-form-input
                                        id="input-1"
                                        v-model="$v.problem.title.$model"
                                        type="text"
                                        :state= "$v.problem.title.$dirty ? !$v.problem.title.$error : null"
                                        required
                                        placeholder="Please enter your problem title here"
                                        aria-describedby="input-1-live-feedback"
                                ></b-form-input>
                                <b-form-invalid-feedback id="input-1-live-feedback">
                                    You must need to enter a title of at lest 10 characters.
                                </b-form-invalid-feedback>
                            </b-form-group>

                            <b-form-group id="input-group-3" label="Problem Body">
                                <vue-editor
                                        v-model="problem.body"
                                        placeholder="Please enter your problem body here"
                                        type="text"
                                        required
                                        :editorToolbar="customToolbar"
                                >
                                </vue-editor>
                                 <b-alert 
                                        :show="dismissCountDownBody" 
                                        dismissible 
                                        variant="danger"  
                                        @dismissed="dismissCountDownBody=0"
                                        @dismiss-count-down="countDownChanged" 
                                        >
                                        Problem body must have at least 100 characters.
                                </b-alert>
                            </b-form-group>
                            <b-form-group id = "input-group-img" label="Select an image (optional)">
                                <b-form-file
                                        v-model="problem.image"
                                        :state="validatImg"
                                        placeholder="Choose an image (.jpeg, .png, .gif) or drop it here..."
                                        drop-placeholder="Drop image here..."
                                        accept="image/jpeg, image/png, image/gif"
                                ></b-form-file>
                            </b-form-group>
                            <b-form-group label="Tags:">
                                <multiselect
                                        v-model="problem.topics_id"
                                        placeholder="Search a tag"
                                        label="name"
                                        track-by="id"
                                        :options="tags"
                                        :multiple="true"
                                        :hide-selected="true"
                                >
                                </multiselect>
                                <b-alert 
                                        :show="dismissCountDownTags" 
                                        dismissible 
                                        variant="danger"  
                                        @dismissed="dismissCountDownTags=0"
                                        @dismiss-count-down="countDownChanged" 
                                        >
                                        You need to select at least one tag.
                                </b-alert>
                            </b-form-group>
                            <b-button type="reset" variant="light"><i class="fas fa-trash fa-1x" style="color:  #e31d1d;"></i></b-button>
                            <b-button variant="light" class="mx-1 float-right " @click="goNext"><i class="fas fa-angle-double-right fa-1x" style="color:  #2f3135 ;"></i></b-button>
                        </b-form>
            </b-container>
        </b-card>
       <h2>Visualizador</h2>
        <b-card class="mt-3" header="Form Data Result">
        <pre class="m-0">{{ problem }}</pre>
        </b-card>
    </div>
</template>

<script>
    import { VueEditor } from "vue2-editor"
    import { mapState } from 'vuex'
    import Multiselect from "vue-multiselect"
    import axios from "axios"
    import { validationMixin } from 'vuelidate'
    import { minLength, required } from 'vuelidate/lib/validators'
    import katex from 'katex';
    import 'katex/dist/katex.min.css';

    export default {
        mixins: [validationMixin],
        data() {
            return {
                dismissSecs: 5,
                dismissCountDownBody: 0,
                dismissCountDownTags: 0,
                tags: []
            }
        },
        mounted() {
            const tag = axios.get("http://localhost:9898/topics/v1/topics/getTopics");
            tag.then(response => (this.tags = response.data));
            window.katex = katex;
        },
        computed: {
            ...mapState ({
                problem: state => state.submit.form.problem,
                customToolbar: state => state.submit.editor
            }),
            validatImg() {
                return (Boolean(this.problem.image)==true? true: null)
            },
        },
        methods: {
            countDownChanged(dismissCountDown) {
                this.dismissCountDown = dismissCountDown
            },
            showAlertBody() {
                this.dismissCountDownBody = this.dismissSecs
            },
            showAlertTags() {
                this.dismissCountDownTags = this.dismissSecs
            },
            goNext() {
                let valTitle = this.problem.title.length > 0 ? true : false;
                let valBody = this.problem.body.length > 100 ? true : false;
                let valTags = this.problem.topics_id.length > 0 ? true: false;
                if (valBody && valTitle && valTags){
                    this.$store.commit('updateViewNext')
                }else{
                    if (!valTags)
                        this.showAlertTags()
                    if (!valBody) 
                        this.showAlertBody()
                }
            },
            onReset(evt) {
                evt.preventDefault();
                this.problem.title = '';
                this.problem.body = '';
                this.problem.image = null;
                this.problem.topics_id = null
            }
        },
        components: {
            VueEditor,
            Multiselect
        },
        validations: {
            problem: {
                title: {
                    required,
                    minLength: minLength(10)
                }
            }
        }
    }
</script>
<style src="../static/css/vue-multiselect/vue-multiselect.min.css"></style>

