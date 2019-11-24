<template>
    <div class="container md-6 align-center mt-2">
        <b-card-group deck>
        <b-card>
            <b-container >
                <h1>Submit a Problem!</h1>
                     <font color="red"> <small>Required *</small> </font> 
                        <b-form @reset="onReset">
                            <b-form-group id="input-group-2" label="Problem Title: *">
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
                                    You must need to have a problem title.
                                </b-form-invalid-feedback>
                                <b-alert 
                                        :show="dismissCountDownTitle" 
                                        dismissible 
                                        variant="danger"  
                                        @dismissed="dismissCountDownTitle=0"
                                        @dismiss-count-down="countDownChanged" 
                                        >
                                        You need to enter a title.
                                </b-alert>
                            </b-form-group>
                                                            
                            <b-form-group label="Problem Body: *">
                                <b-form-textarea
                                id="textarea"
                                v-model="$v.problem.body.$model"
                                :state= "$v.problem.body.$dirty ? !$v.problem.body.$error : null"
                                required
                                placeholder="Please enter your problem body here in latex..."
                                rows="6"
                                max-rows="12"
                                ></b-form-textarea>
                            <b-form-invalid-feedback id="input-2-live-feedback">
                                    You must need to enter a problem body.
                            </b-form-invalid-feedback>
                            <b-alert 
                                        :show="dismissCountDownBody" 
                                        dismissible 
                                        variant="danger"  
                                        @dismissed="dismissCountDownBody=0"
                                        @dismiss-count-down="countDownChanged" 
                                        >
                                        You must need to have a problem body.
                                </b-alert>
                            </b-form-group>
                           
                            <b-form-group label="Tags: *">
                                <multiselect
                                        v-model="$v.problem.topics_id.$model"
                                        :state= "$v.problem.topics_id.$dirty ? !$v.problem.topics_id.$error : null"
                                        placeholder="Search a tag"
                                        label="name"
                                        track-by="id"
                                        :options="tags"
                                        :multiple="true"
                                        :hide-selected="true"
                                >
                                </multiselect>
                                <b-form-invalid-feedback id="input-3-live-feedback">
                                        You must need to select at lest one tag.
                                </b-form-invalid-feedback>
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
                            <!-- Inser a image -->
                            <!-- 
                             <b-form-group id = "input-group-img" label="Select an image: ">
                                <b-form-file
                                        v-model="problem.image"
                                        :state="validatImg"
                                        placeholder="Choose an image (.jpeg, .png, .gif) or drop it here..."
                                        drop-placeholder="Drop image here..."
                                        accept="image/jpeg, image/png, image/gif"
                                ></b-form-file>
                            </b-form-group>
                            -->
                            <b-button type="reset" variant="light"><i class="fas fa-trash fa-1x" style="color:  #e31d1d;"></i></b-button>
                            <b-button variant="light" class="mx-1 float-right " @click="goNext"><i class="fas fa-angle-double-right fa-1x" style="color:  #2f3135 ;"></i></b-button>
                            <b-button variant="light" class="mx-1 float-right " @click="visualize"><i class="fas fa-play-circle"></i></b-button>
                        </b-form>

                      
            </b-container>
        </b-card>
            <b-card>
                <h1>Preview</h1>
                <h3> {{problem.title}} </h3>
               

                <b-card-body v-html="problem_html">
                </b-card-body>
                
                <!--
                <b-card-body v-html="problem_html">
                </b-card-body>
                -->
                
            </b-card>
        </b-card-group>

        
    </div>
</template>
<script>
    //import TexVizualizer from "../components/TexVizualizer";
    import { mapState } from 'vuex'
    import Multiselect from "vue-multiselect"
    import axios from "axios"
    import { validationMixin } from 'vuelidate'
    import { minLength, required } from 'vuelidate/lib/validators'
    import { parse, HtmlGenerator } from 'latex.js'
    import katex from 'katex';
    import 'katex/dist/katex.min.css';
    export default {
        mixins: [validationMixin],
        data() {
            return {
                dismissSecs: 5,
                dismissCountDownBody: 0,
                dismissCountDownTags: 0,
                dismissCountDownTitle: 0,
                tags: [],
                input_types: [
                    {id: 1, name: "Rich Text"},
                    {id: 2, name: "Latex"}
                ],
                input_type: '',
                problem_html: '',
                test: ''
            }
        },
        mounted() {
            window.katex = katex;
            const tag = axios.get("http://localhost:9898/topics/v1/topics/getTopics");
            tag.then(response => (this.tags = response.data));
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
            showAlertTitle() {
                this.dismissCountDownTitle = this.dismissSecs
            },
            goNext() {
                let valTitle = this.problem.title.length > 0 ? true : false;
                let valBody = this.problem.body.length > 0 ? true : false;
                let valTags = this.problem.topics_id.length > 0 ? true: false;
                if (valBody && valTitle && valTags){
                    this.$store.commit('updateViewNext')
                }else{
                    if (!valTags)
                        this.showAlertTags();
                    if (!valBody) 
                        this.showAlertBody();
                    if (!valTitle)
                        this.showAlertTitle();
                }
            },
            onReset (evt) {
                evt.preventDefault();
                this.problem.title = '';
                this.problem.body = '';
                this.problem.image = null;
                this.problem.topics_id = []
            },
            uploadImage (evt) {
                evt.preventDefault();
            },
            visualize(){
                
               const prob = axios.post('http://' + this.$store.state.clientURL +'/problem/v1/problem/latexToHtmlbyBody',{
                    body: this.problem.body
                });
                prob.then(response => (this.test = response.data));
                
                let generator = new HtmlGenerator({ hyphenate: false })
                let doc = parse(this.problem.body, { generator: generator })
                this.problem_html = doc.htmlDocument().documentElement.outerHTML
                
            }

        },
        components: {
            Multiselect,
            //TexVizualizer
        },
        validations: {
            problem: {
                title: {
                    required,
                    minLength: minLength(1)
                },
                body: {
                    required
                },
                topics_id: {
                    required
                }
            }
        }
    }
</script>
<style src="../static/css/vue-multiselect/vue-multiselect.min.css"></style>

